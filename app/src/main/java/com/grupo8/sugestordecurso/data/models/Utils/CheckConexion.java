package com.grupo8.sugestordecurso.data.models.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

//classe para verificar conexao de rede (necessaria pro uso do app)
public class CheckConexion extends LiveData<Boolean> {

    private final ConnectivityManager connectivityManager;
    private ConnectivityManager.NetworkCallback networkCallback;

    public CheckConexion(Context context) {
        this.connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    private void verificarConexaoAtiva() {
        Network network = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            network = connectivityManager.getActiveNetwork();
        }
        if (network == null) {
            postValue(false);
            return;
        }

        NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(network);
        boolean temConexao = capabilities != null &&
                (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET));
        postValue(temConexao);
    }

    private ConnectivityManager.NetworkCallback criarNetworkCallback() {
        return new ConnectivityManager.NetworkCallback() {
            @Override
            public void onAvailable(@NonNull Network network) {
                // A rede está disponível
                postValue(true);
            }

            @Override
            public void onLost(@NonNull Network network) {
                // A rede foi perdida
                postValue(false);
            }
        };
    }

    @Override
    protected void onActive() {
        super.onActive();
        // Verifica o estado inicial da conexão
        verificarConexaoAtiva();

        networkCallback = criarNetworkCallback();
        NetworkRequest networkRequest = new NetworkRequest.Builder()
                .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .build();
        connectivityManager.registerNetworkCallback(networkRequest, networkCallback);
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        if (networkCallback != null) {
            connectivityManager.unregisterNetworkCallback(networkCallback);
            networkCallback = null;
        }
    }
}
