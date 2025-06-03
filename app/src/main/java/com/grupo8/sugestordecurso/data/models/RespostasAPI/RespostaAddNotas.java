package com.grupo8.sugestordecurso.data.models.RespostasAPI;

import java.util.ArrayList;

class DadosNotas {
}

public class RespostaAddNotas {
    private boolean success;
    private ArrayList<DadosUser> dados;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

}
