package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

public class Controller {

    public TextField txtFieldCName;
    public TextField txtFieldCPass;
    public TextField txtFieldRFrom;
    public TextField txtFieldRTo;
    public TextField txtFieldUID;
    public TextField txtFieldUName;
    public TextField txtFieldUPass;
    public TextField txtFieldDID;

    DataBaseHelper myDBHelper = new DataBaseHelper();//only create new object

    public void doAction(ActionEvent actionEvent) {//not work with this method
        String txtFld = txtFieldCName.getText();
        myDBHelper.insert(txtFld, "456789");
    }

    public void doDelete(ActionEvent actionEvent) {
        int txtFldId = Integer.parseInt(txtFieldDID.getText());
        myDBHelper.delete(txtFldId);
    }

    public void doUpdate(ActionEvent actionEvent) {
        String name = txtFieldUName.getText();
        String pass = txtFieldUPass.getText();
        int txtFldId = Integer.parseInt(txtFieldUID.getText());
        myDBHelper.update(name, pass, txtFldId);
    }

    public void doPrint(ActionEvent actionEvent) {
        myDBHelper.printAll();
    }

    public void doSafeStart(ActionEvent actionEvent) {
        String txtFldName = txtFieldCName.getText();
        String txtFldPass = txtFieldCPass.getText();
        myDBHelper.preparedInseert(txtFldName, txtFldPass);
    }

    public void doPrintWithLimit(ActionEvent actionEvent) {

    }

    public void doDeleteAll(ActionEvent actionEvent) {
        myDBHelper.deleteAll();
    }
}
