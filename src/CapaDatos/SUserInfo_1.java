/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package CapaDatos;

import com.jcraft.jsch.UserInfo;

/**
 *
 * @author nortfarma
 */
class SUserInfo_1 implements UserInfo{

    private String password;
    private String passPhrase;

    public SUserInfo_1(String pass, Object object) {
        this.password = password;
        this.passPhrase = passPhrase;
    }

     @Override
    public String getPassphrase() {
        return passPhrase;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean promptPassphrase(String arg0) {
        return true;
    }

    @Override
    public boolean promptPassword(String arg0) {
        return false;
    }

    @Override
    public boolean promptYesNo(String arg0) {
        return true;
    }

    @Override
    public void showMessage(String arg0) {
        System.out.println("SUserInfo.showMessage()");
    }




}
