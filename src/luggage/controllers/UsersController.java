/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 FastenYourSeatbelts
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */
package luggage.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import luggage.Debug;
import luggage.MainActivity;
import luggage.database.models.LocationModel;
import luggage.database.models.UserModel;
import luggage.database.models.Model;
import luggage.helpers.StageHelper;
import luggage.security.Encryption;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;

/**
 * UsersController
 *
 * Controller for users/list.fxml
 *
 * @package luggage.controllers
 * @author Alexander + Nick
 */
public class UsersController extends BaseController implements Initializable {

    @FXML
    private TableView listTableView;

    @FXML
    private TableColumn listTableViewUsername;

    @FXML
    private TableColumn listTableViewName;

    @FXML
    private TableColumn listTableViewInsurer;

    @FXML
    private TableColumn listTableViewAddress;

    @FXML
    private TableColumn listTableViewPhone;

    @FXML
    private TableColumn listTableViewEmail;

    @FXML
    private TableColumn listTableViewRole;

    @FXML
    private TextField listSearchField;

    @FXML
    private Button newAdd;

    @FXML
    private Button newReset;

    @FXML
    private Button newCancel;

    /*
     *all ADD fields
     */
    @FXML
    private TextField addAddress;

    @FXML
    private TextField addPostalcode;

    @FXML
    private TextField addResidence;

    @FXML
    private ChoiceBox<LocationModel> addWorkplace;

    @FXML
    private ChoiceBox addRole;

    @FXML
    private TextField addTelephone;

    @FXML
    private TextField addMobile;

    @FXML
    private TextField addFirstname;

    @FXML
    private TextField addPrefix;

    @FXML
    private TextField addLastname;

    @FXML
    private ChoiceBox addGender;

    @FXML
    private TextField addUsername;

    @FXML
    private TextField addPassword;

    @FXML
    private TextField addPasswordRepeat;

    /*
     * all EDIT fields
     */
    @FXML
    private Button editAdd;

    @FXML
    private TextField editFirstname;

    @FXML
    private TextField editPrefix;

    @FXML
    private TextField editLastname;

    @FXML
    private TextField editUsername;

    @FXML
    private TextField editPassword;

    @FXML
    private TextField editPasswordRepeat;

    @FXML
    private TextField editAddress;

    @FXML
    private TextField editPostalcode;

    @FXML
    private TextField editResidence;

    @FXML
    private TextField editTelephone;

    @FXML
    private TextField editMobile;

    @FXML
    private ChoiceBox editGender;

    @FXML
    private ChoiceBox editRole;

    @FXML
    private ChoiceBox<LocationModel> editWorkplace;

    @FXML
    private Button editReset;

    @FXML
    private Button editSave;

    @FXML
    private Button editCancel;

    /**
     * VIEW ELEMENTS
     */
    @FXML
    private Button listView;

    @FXML
    private Button listEdit;

    @FXML
    private Button listRemove;

    @FXML
    private TextField viewUsername;

    @FXML
    private TextField viewPassword;

    @FXML
    private TextField viewFirstname;

    @FXML
    private TextField viewPrefix;

    @FXML
    private TextField viewLastname;

    @FXML
    private TextField viewAddress;

    @FXML
    private TextField viewPostalcode;

    @FXML
    private TextField viewResidence;

    @FXML
    private TextField viewTelephone;

    @FXML
    private TextField viewMobile;

    @FXML
    private ChoiceBox viewRole;

    @FXML
    private ChoiceBox viewGender;

    @FXML
    private ChoiceBox viewWorkplace;

    @FXML
    private Button viewClose;

    private ObservableList<UserModel> listData = FXCollections.observableArrayList();
    
    private final ObservableList<LocationModel> workplaceData = FXCollections.observableArrayList();

    @FXML
    public void listOnSearch() {

        String[] keywords = listSearchField.getText().split("\\s+");

        String[] params = new String[3 * keywords.length];
        boolean firstColumn = true;
        String query = "";

        for (int i = 0; i < keywords.length; i++) {
            if (firstColumn) {
                params[0 + i] = "%" + keywords[i] + "%";
                query += "username LIKE ?";
            } else {
                params[0 + i] = "%" + keywords[i] + "%";
                query += " OR username LIKE ?";
            }

            params[1 + i] = "%" + keywords[i] + "%";
            query += " OR firstname LIKE ?";

            params[2 + i] = "%" + keywords[i] + "%";
            query += " OR lastname LIKE ?";

            firstColumn = false;
        }

        listResetTableView(query, params);
    }

    private ObservableList<UserModel> data = FXCollections.observableArrayList();

    /**
     * Called on controller start
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Debug.print("USERS CONTROLLER-----------------------------------------------------------------");

                // List
                if (listTableView != null) {
                    listResetTableView("", new String[0]);

                    listEdit.disableProperty().bind(listTableView.getSelectionModel().selectedItemProperty().isNull());
                    listRemove.disableProperty().bind(listTableView.getSelectionModel().selectedItemProperty().isNull());
                    listView.disableProperty().bind(listTableView.getSelectionModel().selectedItemProperty().isNull());
                    listTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                }

                // Add
                if (addGender != null && addRole != null && addWorkplace != null) {
                    setAddChoiceBox();
                }

                //Edit
                if (editGender != null && editRole != null && editWorkplace != null) {
                    setEditChoiceBoxes();
                    setEditFields();
                }

                // View
                if (viewGender != null && viewRole != null && viewWorkplace != null) {
                    setViewChoiceBoxes();
                    setViewFields();
                }

            }
        });
    }

    public void setViewChoiceBoxes() {
        viewGender.setItems(FXCollections.observableArrayList(
            "MALE",
            "FEMALE",
            "OTHER"
        ));

        viewRole.setItems(FXCollections.observableArrayList(
            "EMPLOYEE",
            "MANAGER",
            "MODERATOR",
            "SUPER"
        ));
        
        LocationModel locations = new LocationModel();
        List<Model> allLocations = locations.findAll("", new String[0]);
        
        int locationId = new UserModel(MainActivity.viewId).getLocation().getId();
        for(Model allLocation : allLocations) {
            LocationModel location = (LocationModel) allLocation;
            if(location.getId() == locationId)
            {
                selectedWorkplace = location;
            }
            
            workplaceData.add(location);
        }

        viewWorkplace.setItems(workplaceData);
    }

    public void setAddChoiceBox() {
        addGender.setItems(FXCollections.observableArrayList(
            "MALE",
            "FEMALE",
            "OTHER"
        ));

        addRole.setItems(FXCollections.observableArrayList(
            "EMPLOYEE",
            "MANAGER",
            "MODERATOR",
            "SUPER"
        ));
        
        LocationModel locations = new LocationModel();
        List<Model> allLocations = locations.findAll("", new String[0]);
        
        for(Model allLocation : allLocations) {
            LocationModel location = (LocationModel) allLocation;
            workplaceData.add(location);
        }

        addWorkplace.setItems(workplaceData);
    }

    public void setEditFields() {
        UserModel user = new UserModel(MainActivity.editId);

        editUsername.setText(user.getUsername());
        editFirstname.setText(user.getFirstname());
        editPrefix.setText(user.getPrefix());
        editLastname.setText(user.getLastname());
        editAddress.setText(user.getAddress());
        editPostalcode.setText(user.getPostalcode());
        editResidence.setText(user.getResidence());
        editTelephone.setText(user.getTelephone());
        editMobile.setText(user.getMobile());

        editGender.getSelectionModel().select(user.getGender().toUpperCase());
        editRole.getSelectionModel().select(user.getRole().toUpperCase());
        editWorkplace.getSelectionModel().select(selectedWorkplace);
    }
    
    public LocationModel selectedWorkplace;

    public void setEditChoiceBoxes() {
        editGender.setItems(FXCollections.observableArrayList(
            "MALE",
            "FEMALE",
            "OTHER"
        ));

        editRole.setItems(FXCollections.observableArrayList(
            "EMPLOYEE",
            "MANAGER",
            "MODERATOR",
            "SUPER"
        ));
        
        LocationModel locations = new LocationModel();
        List<Model> allLocations = locations.findAll("", new String[0]);
        
        int locationId = new UserModel(MainActivity.editId).getLocationId();
        for(Model allLocation : allLocations) {
            LocationModel location = (LocationModel) allLocation;
            if(location.getId() == locationId)
            {
                selectedWorkplace = location;
            }
            
            workplaceData.add(location);
        }

        editWorkplace.setItems(workplaceData);
    }

    public void listResetTableView(String where, String... params) {
        UserModel users = new UserModel();
        List<Model> allUsers = users.findAll(where, params);

        data = FXCollections.observableArrayList();
        for (Model allUser : allUsers) {
            UserModel user = (UserModel) allUser;
            data.add(user);
        }

        listTableViewUsername.setCellValueFactory(new PropertyValueFactory("username"));
        listTableViewName.setCellValueFactory(new PropertyValueFactory("fullname"));
        listTableViewRole.setCellValueFactory(new PropertyValueFactory("role"));

        listTableView.setItems(data);
    }

    @FXML
    public void listNew() {
        StageHelper.addPopup("users/add", this, false, true);
    }

    @FXML
    public void listEdit() {
        UserModel user = (UserModel) listTableView.getSelectionModel().getSelectedItem();

        if (user == null) {
            return;
        }

        MainActivity.editId = user.getId();

        StageHelper.addPopup("users/edit", this, false, true);
    }

    @FXML
    public void listRemove() {
        Stage removeStage = (Stage) listTableView.getScene().getWindow();

        Action response = Dialogs.create().owner(removeStage)
                .title("Are you sure you want to delete this item?")
                .message("Are you sure you want to delete this item?")
                .actions(Dialog.ACTION_OK, Dialog.ACTION_CANCEL)
                .showWarning();

        if (response == Dialog.ACTION_OK) {
            UserModel user = (UserModel) listTableView.getSelectionModel().getSelectedItem();

            if (user == null) {
                return;
            }

            user.delete();
            listOnSearch();
        } else {
            return;
        }
    }

    @FXML
    public void listView() {
        UserModel user = (UserModel) listTableView.getSelectionModel().getSelectedItem();

        if (user == null) {
            return;
        }

        MainActivity.viewId = user.getId();

        StageHelper.addPopup("users/view", this, false, true);

    }

    public void newCancel() {
        Stage addStage = (Stage) newCancel.getScene().getWindow();
        StageHelper.closeStage(addStage);
    }

    public void newReset() {
        addPassword.setText("");
        addPasswordRepeat.setText("");
        addUsername.setText("");
        addFirstname.setText("");
        addPrefix.setText("");
        addLastname.setText("");
        addAddress.setText("");
        addPostalcode.setText("");
        addResidence.setText("");
        addTelephone.setText("");
        addMobile.setText("");
        addGender.setValue(null);
        addRole.setValue("EMPLOYEE");
        addWorkplace.setValue(null);
    }

    public void newSave() {
        if (addGender.getSelectionModel().getSelectedItem() == null || addRole.getSelectionModel().getSelectedItem() == null || addWorkplace.getSelectionModel().getSelectedItem() == null) {
            Dialogs.create()
                .owner((Stage) addPassword.getScene().getWindow())
                .title("Warning")
                .masthead("Selection error")
                .message("Please enter all the select boxes.")
                .showWarning();
            return;
        }
        
        if(!addPassword.getText().equals(addPasswordRepeat.getText()))
        {
            Dialogs.create()
                .owner((Stage) addPassword.getScene().getWindow())
                .title("Warning")
                .masthead("Password error")
                .message("The passwords do not match.")
                .showWarning();
            return;
        }

        UserModel users = new UserModel();
        users.setPassword(Encryption.hash(addPassword.getText()));
        users.setUsername(addUsername.getText());
        users.setFirstname(addFirstname.getText());
        users.setPrefix(addPrefix.getText());
        users.setLastname(addLastname.getText());
        users.setGender(addGender.getSelectionModel().getSelectedItem().toString());
        users.setAddress(addAddress.getText());
        users.setPostalcode(addPostalcode.getText());
        users.setResidence(addResidence.getText());
        users.setLocationId(Integer.toString(addWorkplace.getSelectionModel().getSelectedItem().getId()));
        users.setRole(addRole.getSelectionModel().getSelectedItem().toString());
        users.setTelephone(addTelephone.getText());
        users.setMobile(addMobile.getText());
        users.save();

        UsersController usersController = (UsersController) StageHelper.callbackController;
        usersController.listOnSearch();

        newCancel();
    }

    public void editReset() {
        editUsername.setText("");
        editPassword.setText("");
        editPasswordRepeat.setText("");
        editFirstname.setText("");
        editPrefix.setText("");
        editLastname.setText("");
        editPostalcode.setText("");
        editAddress.setText("");
        editResidence.setText("");
        editTelephone.setText("");
        editMobile.setText("");
        editGender.setValue(null);
        editRole.setValue("EMPLOYEE");
        editWorkplace.setValue(null);
    }

    public void editSave() {
        if (editGender.getSelectionModel().getSelectedItem() == null || editRole.getSelectionModel().getSelectedItem() == null || editWorkplace.getSelectionModel().getSelectedItem() == null) {
            Dialogs.create()
                .owner((Stage) editPassword.getScene().getWindow())
                .title("Warning")
                .masthead("Selection error")
                .message("Please enter all the select boxes.")
                .showWarning();
            return;
        }
        
        if(!editPassword.getText().equals(editPasswordRepeat.getText()))
        {
            Dialogs.create()
                .owner((Stage) editPassword.getScene().getWindow())
                .title("Warning")
                .masthead("Password error")
                .message("The passwords do not match.")
                .showWarning();
            return;
        }

        UserModel user = new UserModel(MainActivity.editId);
        user.setUsername(editUsername.getText());
        
        if(!editPassword.getText().equals(""))
        {
            user.setPassword(Encryption.hash(editPassword.getText()));
        }
        
        user.setFirstname(editFirstname.getText());
        user.setPrefix(editPrefix.getText());
        user.setLastname(editLastname.getText());
        user.setPostalcode(editPostalcode.getText());
        user.setAddress(editAddress.getText());
        user.setResidence(editResidence.getText());
        user.setLocationId(Integer.toString(editWorkplace.getSelectionModel().getSelectedItem().getId()));
        user.setTelephone(editTelephone.getText());
        user.setMobile(editMobile.getText());
        user.save();

        UsersController userController = (UsersController) StageHelper.callbackController;
        userController.listOnSearch();

        editCancel();
    }

    public void setViewFields() {
        UserModel user = new UserModel(MainActivity.viewId);

        viewUsername.setText(user.getUsername());
        viewFirstname.setText(user.getFirstname());
        viewPrefix.setText(user.getPrefix());
        viewLastname.setText(user.getLastname());
        viewAddress.setText(user.getAddress());
        viewPostalcode.setText(user.getPostalcode());
        viewResidence.setText(user.getResidence());
        viewTelephone.setText(user.getTelephone());
        viewMobile.setText(user.getMobile());

        viewGender.getSelectionModel().select(user.getGender().toUpperCase());
        viewRole.getSelectionModel().select(user.getRole().toUpperCase());
        viewWorkplace.getSelectionModel().select(selectedWorkplace);
    }

    public void viewClose() {
        Stage cancelStage = (Stage) viewClose.getScene().getWindow();
        StageHelper.closeStage(cancelStage);
    }

    public void editCancel() {
        Stage cancelStage = (Stage) editCancel.getScene().getWindow();
        StageHelper.closeStage(cancelStage);
    }
}
