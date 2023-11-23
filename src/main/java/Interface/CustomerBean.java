package Interface;

import java.io.Serializable;

public class CustomerBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private String emailUser = "";



    private boolean esAdmin;

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public boolean isEsAdmin() {
        return esAdmin;
    }

    public void setEsAdmin(boolean esAdmin) {
        this.esAdmin = esAdmin;
    }
}
