/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2014-2015 ITopia IS102-5
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import luggage.Debug;
import luggage.MainActivity;
import luggage.database.models.CustomerModel;
import luggage.database.models.InsurerModel;
import luggage.database.models.Model;
import luggage.helpers.StageHelper;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;

/**
 * CustomersController
 *
 * Controller for customers/list.fxml, customers/new.fxml, customers/edit.fxml,
 * customers/view.fxml and customers/help.fxml
 *
 * Package: luggage.controllers
 *
 * @author ITopia IS102-5
 */
public class CustomersController extends BaseController implements Initializable {

    /**
     * LIST ELEMENTS
     */
    @FXML
    private AnchorPane listGeneral;

    /**
     *
     */
    @FXML
    private AnchorPane helpGeneral;

    /**
     *
     */
    @FXML
    private TableView listTableView;

    /**
     *
     */
    @FXML
    private TableColumn listTableViewName;

    /**
     *
     */
    @FXML
    private TableColumn listTableViewAddress;

    /**
     *
     */
    @FXML
    private TableColumn listTableViewPhone;

    /**
     *
     */
    @FXML
    private TableColumn listTableViewEmail;

    /**
     *
     */
    @FXML
    private Button listEdit;

    /**
     *
     */
    @FXML
    private Button listHelp;

    /**
     *
     */
    @FXML
    private Button listNew;

    /**
     *
     */
    @FXML
    private Button listRemove;

    /**
     *
     */
    @FXML
    private Button listView;

    /**
     *
     */
    @FXML
    private Label printNotif;

    /**
     *
     */
    @FXML
    private TextField listSearchField;

    /**
     * NEW ELEMENTS
     */
    @FXML
    private TextField newFirstname;

    /**
     *
     */
    @FXML
    private TextField newPrefix;

    /**
     *
     */
    @FXML
    private TextField newLastname;

    /**
     *
     */
    @FXML
    private ChoiceBox newGender;

    /**
     *
     */
    @FXML
    private ChoiceBox<InsurerModel> newInsurerId;

    /**
     *
     */
    @FXML
    private TextField newAddress;

    /**
     *
     */
    @FXML
    private TextField newPostalcode;

    /**
     *
     */
    @FXML
    private TextField newResidence;

    /**
     *
     */
    @FXML
    private TextField newAddress2;

    /**
     *
     */
    @FXML
    private TextField newPostalcode2;

    /**
     *
     */
    @FXML
    private TextField newResidence2;

    /**
     *
     */
    @FXML
    private TextField newEmail;

    /**
     *
     */
    @FXML
    private TextField newTelephone;

    /**
     *
     */
    @FXML
    private TextField newMobile;

    /**
     *
     */
    @FXML
    private Button newSave;

    /**
     *
     */
    @FXML
    private Button newReset;

    /**
     *
     */
    @FXML
    private Button newCancel;

    /**
     * EDIT ELEMENTS
     */
    @FXML
    private Button editSave;

    /**
     *
     */
    @FXML
    private Button editReset;

    /**
     *
     */
    @FXML
    private Button editCancel;

    /**
     *
     */
    @FXML
    private TextField editFirstname;

    /**
     *
     */
    @FXML
    private TextField editPrefix;

    /**
     *
     */
    @FXML
    private TextField editLastname;

    /**
     *
     */
    @FXML
    private ChoiceBox editGender;

    /**
     *
     */
    @FXML
    private ChoiceBox<InsurerModel> editInsurerId;

    /**
     *
     */
    @FXML
    private TextField editAddress;

    /**
     *
     */
    @FXML
    private TextField editPostalcode;

    /**
     *
     */
    @FXML
    private TextField editResidence;

    /**
     *
     */
    @FXML
    private TextField editAddress2;

    /**
     *
     */
    @FXML
    private TextField editPostalcode2;

    /**
     *
     */
    @FXML
    private TextField editResidence2;

    /**
     *
     */
    @FXML
    private TextField editEmail;

    /**
     *
     */
    @FXML
    private TextField editTelephone;

    /**
     *
     */
    @FXML
    private TextField editMobile;

    /**
     * VIEW ELEMENTS
     */
    @FXML
    private Button viewClose;

    /**
     *
     */
    @FXML
    private Button viewCustomersLuggage;

    /**
     *
     */
    @FXML
    private ChoiceBox viewGender;

    /**
     *
     */
    @FXML
    private ChoiceBox<InsurerModel> viewInsurerId;

    /**
     *
     */
    @FXML
    private TextField viewFirstname;

    /**
     *
     */
    @FXML
    private TextField viewPrefix;

    /**
     *
     */
    @FXML
    private TextField viewLastname;

    /**
     *
     */
    @FXML
    private TextField viewGenderAsText;

    /**
     *
     */
    @FXML
    private TextField viewInsurerAsText;

    /**
     *
     */
    @FXML
    private TextField viewAddress;

    /**
     *
     */
    @FXML
    private TextField viewPostalcode;

    /**
     *
     */
    @FXML
    private TextField viewResidence;

    /**
     *
     */
    @FXML
    private TextField viewAddress2;

    /**
     *
     */
    @FXML
    private TextField viewPostalcode2;

    /**
     *
     */
    @FXML
    private TextField viewResidence2;

    /**
     *
     */
    @FXML
    private TextField viewEmail;

    /**
     *
     */
    @FXML
    private TextField viewTelephone;

    /**
     *
     */
    @FXML
    private TextField viewMobile;

    /**
     *
     */
    private ObservableList<CustomerModel> listData = FXCollections.observableArrayList();

    /**
     *
     */
    private final ObservableList<InsurerModel> insurerData = FXCollections.observableArrayList();

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
                Debug.print("CUSTOMERS CONTROLLER-----------------------------------------------------------------");

                // List
                if (listTableView != null) {
                    listResetTableView("", new String[0]);

                    listEdit.disableProperty().bind(listTableView.getSelectionModel().selectedItemProperty().isNull());
                    listRemove.disableProperty().bind(listTableView.getSelectionModel().selectedItemProperty().isNull());
                    listView.disableProperty().bind(listTableView.getSelectionModel().selectedItemProperty().isNull());
                    listTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                    listKeyActions();
                }

                // New
                if (newGender != null && newInsurerId != null) {
                    setNewChoiceBoxes();
                    newKeyActions();
                }

                // Edit
                if (editGender != null && editInsurerId != null) {
                    setEditChoiceBoxes();
                    setEditFields();
                    editKeyActions();
                }

                // View
                if (viewGender != null && viewInsurerId != null) {
                    setViewChoiceBoxes();
                    setViewFields();
                    viewKeyActions();
                }

                // Help
                if (helpGeneral != null) {
                    helpKeyAction();
                }
            }
        });
    }

    /**
     * Calls InsurerModel to enable mapping an Insurer ID to the Insurer's name.
     */
    public InsurerModel selectedInsurer;

    /**
     * Populates the view fields with the selected Customer's data.
     */
    public void setViewFields() {
        CustomerModel customer = new CustomerModel(MainActivity.viewId);

        viewFirstname.setText(customer.getFirstname());
        viewPrefix.setText(customer.getPrefix());
        viewLastname.setText(customer.getLastname());
        viewAddress.setText(customer.getAddress());
        viewPostalcode.setText(customer.getPostalCode());
        viewResidence.setText(customer.getResidence());
        viewAddress2.setText(customer.getAddress2());
        viewPostalcode2.setText(customer.getPostalCode2());
        viewResidence2.setText(customer.getResidence2());
        viewEmail.setText(customer.getEmail());
        viewTelephone.setText(customer.getTelephone());
        viewMobile.setText(customer.getMobile());

        viewGender.getSelectionModel().select(customer.getGender());
//        viewInsurerId.getSelectionModel().select(selectedInsurer);
        viewInsurerAsText.setText(selectedInsurer.toString());
        viewGenderAsText.setText(viewGender.getValue().toString());
    }

    /**
     * Populates the view Gender &amp; Insurer ChoiceBoxes.
     */
    public void setViewChoiceBoxes() {
        viewGender.setItems(FXCollections.observableArrayList(
                "Male",
                "Female",
                "Other"
        ));

        InsurerModel insurers = new InsurerModel();
        List<Model> allInsurers = insurers.findAll("", new String[0]);

        int insurerId = new CustomerModel(MainActivity.viewId).getInsurerId();

        for (Model allInsurer : allInsurers) {
            InsurerModel insurer = (InsurerModel) allInsurer;
            if (insurer.getId() == insurerId) {
                selectedInsurer = insurer;
            }
            insurerData.add(insurer);
        }

        viewInsurerId.setItems(insurerData);
    }

    /**
     * Populates the edit fields with the selected Customer's data.
     */
    public void setEditFields() {
        CustomerModel customer = new CustomerModel(MainActivity.editId);

        editFirstname.setText(customer.getFirstname());
        editPrefix.setText(customer.getPrefix());
        editLastname.setText(customer.getLastname());
        editAddress.setText(customer.getAddress());
        editPostalcode.setText(customer.getPostalCode());
        editResidence.setText(customer.getResidence());
        editAddress2.setText(customer.getAddress2());
        editPostalcode2.setText(customer.getPostalCode2());
        editResidence2.setText(customer.getResidence2());
        editEmail.setText(customer.getEmail());
        editTelephone.setText(customer.getTelephone());
        editMobile.setText(customer.getMobile());

        editInsurerId.getSelectionModel().select(selectedInsurer);
        editGender.getSelectionModel().select(customer.getGender());
    }

    /**
     * Populates the view Gender &amp; Insurer ChoiceBoxes.
     */
    public void setEditChoiceBoxes() {
        editGender.setItems(FXCollections.observableArrayList(
                "Male",
                "Female",
                "Other"
        ));

        InsurerModel insurers = new InsurerModel();
        List<Model> allInsurers = insurers.findAll("", new String[0]);

        int selectedInsurerId = new CustomerModel(MainActivity.editId).getInsurerId();

        for (Model allInsurer : allInsurers) {
            InsurerModel insurer = (InsurerModel) allInsurer;
            if (insurer.getId() == selectedInsurerId) {
                selectedInsurer = insurer;
            }
            insurerData.add(insurer);
        }

        editInsurerId.setItems(insurerData);
    }

    /**
     * Populates the new view's Gender &amp; Insurer ChoiceBoxes.
     */
    public void setNewChoiceBoxes() {
        newGender.setItems(FXCollections.observableArrayList(
                "Male",
                "Female",
                "Other"
        ));

        InsurerModel insurers = new InsurerModel();
        List<Model> allInsurers = insurers.findAll("", new String[0]);
        for (Model allInsurer : allInsurers) {
            InsurerModel insurer = (InsurerModel) allInsurer;
            insurerData.add(insurer);
        }

        newInsurerId.setItems(insurerData);
    }

    /**
     * Populates the list (TableView) with the provided query as parameter
     * (searching the database).
     *
     * @param where
     * @param params
     */
    public void listResetTableView(String where, String... params) {
        CustomerModel customers = new CustomerModel();
        List<Model> allCustomers = customers.findAll(where, params);

        listData = FXCollections.observableArrayList();
        for (Model allCustomer : allCustomers) {
            CustomerModel customer = (CustomerModel) allCustomer;
            listData.add(customer);
        }

        listTableViewName.setCellValueFactory(new PropertyValueFactory("fullname"));
        listTableViewAddress.setCellValueFactory(new PropertyValueFactory("address"));
        listTableViewPhone.setCellValueFactory(new PropertyValueFactory("telephone"));
        listTableViewEmail.setCellValueFactory(new PropertyValueFactory("email"));

        listTableView.setItems(listData);
    }

    /**
     * Handles the search field functionality.
     */
    @FXML
    public void listOnSearch() {
        String[] keywords = listSearchField.getText().split("\\s+");

        String[] params = new String[5 * keywords.length];
        boolean firstColumn = true;
        String query = "";

        for (int i = 0; i < keywords.length; i++) {
            if (firstColumn) {
                params[0 + i] = "%" + keywords[i] + "%";
                query += "firstname LIKE ?";
            } else {
                params[0 + i] = "%" + keywords[i] + "%";
                query += " OR firstname LIKE ?";
            }

            params[1 + i] = "%" + keywords[i] + "%";
            query += " OR lastname LIKE ?";

            params[2 + i] = "%" + keywords[i] + "%";
            query += " OR address LIKE ?";

            params[3 + i] = "%" + keywords[i] + "%";
            query += " OR email LIKE ?";

            params[4 + i] = "%" + keywords[i] + "%";
            query += " OR mobile LIKE ?";

            firstColumn = false;
        }

        listResetTableView(query, params);
    }

    /**
     * Opens the 'New Customer' view.
     */
    @FXML
    public void listNew() {
        StageHelper.addPopup("customers/new", this, false, true);
    }

    /**
     * Opens the Customer list's help view.
     */
    @FXML
    public void listHelp() {
        StageHelper.addStage("customers/help", this, false, true);
    }

    /**
     * Opens the Customer edit view for the selected customer.
     */
    @FXML
    public void listEdit() {
        CustomerModel customer = (CustomerModel) listTableView.getSelectionModel().getSelectedItem();

        if (customer == null) {
            return;
        }

        MainActivity.editId = customer.getId();

        StageHelper.addPopup("customers/edit", this, false, true);
    }

    /**
     * Triggers a confirmation dialog for removing the selected customer.
     */
    @FXML
    public void listRemove() {
        Stage removeStage = (Stage) listTableView.getScene().getWindow();

        Action response = Dialogs.create().owner(removeStage)
                .title("Remove customer")
                //.masthead("Are you sure you want to delete this item? 2")
                .message("Are you sure you want to delete this customer?")
                .actions(Dialog.ACTION_OK, Dialog.ACTION_CANCEL)
                .showWarning();

        if (response == Dialog.ACTION_OK) {
            CustomerModel customer = (CustomerModel) listTableView.getSelectionModel().getSelectedItem();

            if (customer == null) {
                return;
            }

            customer.delete();
            listOnSearch();
        }
    }

    /**
     * Opens the Customer list view.
     */
    @FXML
    public void listView() {
        CustomerModel customer = (CustomerModel) listTableView.getSelectionModel().getSelectedItem();

        if (customer == null) {
            return;
        }

        MainActivity.viewId = customer.getId();

        StageHelper.addPopup("customers/view", this, false, true);
    }

    /**
     * Handles canceling and closing the new view.
     */
    @FXML
    private void newCancel() {
        Stage addStage = (Stage) newCancel.getScene().getWindow();
        StageHelper.closeStage(addStage);
    }

    /**
     * Resets all fields in the new view.
     */
    @FXML
    private void newReset() {
        newFirstname.setText("");
        newPrefix.setText("");
        newLastname.setText("");
        newAddress.setText("");
        newPostalcode.setText("");
        newResidence.setText("");
        newAddress2.setText("");
        newPostalcode2.setText("");
        newResidence2.setText("");
        newEmail.setText("");
        newTelephone.setText("");
        newMobile.setText("");
        newGender.setValue(null);
        newInsurerId.setValue(null);
    }

    /**
     * Handles saving a new Customer. Checks if all necessary fields are given
     * and if so, writes to database.
     */
    @FXML
    private void newSave() {
        if (newFirstname.getText().equals("") || newLastname.getText().equals("")) {
            Dialogs.create()
                    .owner((Stage) newLastname.getScene().getWindow())
                    .title("Warning")
                    .masthead("Entry error")
                    .message("Please enter the customer's first and last name.")
                    .showWarning();
            return;
        } else if (newGender.getSelectionModel().getSelectedItem() == null) {
            Dialogs.create()
                    .owner((Stage) newGender.getScene().getWindow())
                    .title("Warning")
                    .masthead("Selection error")
                    .message("Please enter the gender.")
                    .showWarning();
            return;
        } else if (newInsurerId.getSelectionModel().getSelectedItem() == null) {
            Dialogs.create()
                    .owner((Stage) newInsurerId.getScene().getWindow())
                    .title("Warning")
                    .masthead("Selection error")
                    .message("Please enter the insurer.")
                    .showWarning();
            return;
        } else if (newAddress.getText().equals("")) {
            Dialogs.create()
                    .owner((Stage) newAddress.getScene().getWindow())
                    .title("Warning")
                    .masthead("Entry error")
                    .message("Please enter the customer's address.")
                    .showWarning();
            return;
        } else if (newPostalcode.getText().equals("")) {
            Dialogs.create()
                    .owner((Stage) newPostalcode.getScene().getWindow())
                    .title("Warning")
                    .masthead("Entry error")
                    .message("Please enter the customer's postal code.")
                    .showWarning();
            return;
        } else if (newResidence.getText().equals("")) {
            Dialogs.create()
                    .owner((Stage) newResidence.getScene().getWindow())
                    .title("Warning")
                    .masthead("Entry error")
                    .message("Please enter the customer's residence.")
                    .showWarning();
            return;
        } else if (newEmail.getText().equals("")) {
            Dialogs.create()
                    .owner((Stage) newEmail.getScene().getWindow())
                    .title("Warning")
                    .masthead("Entry error")
                    .message("Please enter the customer's email address.")
                    .showWarning();
            return;
        } else if (newTelephone.getText().equals("")) {
            Dialogs.create()
                    .owner((Stage) newTelephone.getScene().getWindow())
                    .title("Warning")
                    .masthead("Entry error")
                    .message("Please enter at least one phone number in the first text box for the customer.")
                    .showWarning();
            return;
        }

        CustomerModel customer = new CustomerModel();
        customer.setFirstname(newFirstname.getText());
        customer.setPrefix(newPrefix.getText());
        customer.setLastname(newLastname.getText());
        customer.setGender(newGender.getSelectionModel().getSelectedItem().toString());
        customer.setInsurerId(Integer.toString(newInsurerId.getSelectionModel().getSelectedItem().getId()));
        customer.setAddress(newAddress.getText());
        customer.setPostalCode(newPostalcode.getText());
        customer.setResidence(newResidence.getText());
        customer.setAddress2(newAddress2.getText());
        customer.setPostalCode2(newPostalcode2.getText());
        customer.setResidence2(newResidence2.getText());
        customer.setEmail(newEmail.getText());
        customer.setTelephone(newTelephone.getText());
        customer.setMobile(newMobile.getText());
        customer.save();

        CustomersController customersController = (CustomersController) StageHelper.callbackController;
        customersController.listOnSearch();

        newCancel();
    }

    /**
     * Cancels editing a Customer, does not change saved data.
     */
    @FXML
    private void editCancel() {
        Stage cancelStage = (Stage) editCancel.getScene().getWindow();
        StageHelper.closeStage(cancelStage);
    }

    /**
     * Resets all fields in the edit view.
     */
    @FXML
    private void editReset() {
        editFirstname.setText("");
        editPrefix.setText("");
        editLastname.setText("");
        editAddress.setText("");
        editPostalcode.setText("");
        editResidence.setText("");
        editAddress2.setText("");
        editPostalcode2.setText("");
        editResidence2.setText("");
        editEmail.setText("");
        editTelephone.setText("");
        editMobile.setText("");
        editGender.setValue(null);
        editInsurerId.setValue(null);
    }

    /**
     * Handles saving changes to an existing Customer. Checks if all necessary
     * fields are filled and if so, writes to database, overwriting existing
     * data for selected Customer.
     */
    @FXML
    private void editSave() {
        if (editFirstname.getText().equals("") || editLastname.getText().equals("")) {
            Dialogs.create()
                    .owner((Stage) editLastname.getScene().getWindow())
                    .title("Warning")
                    .masthead("Entry error")
                    .message("Please enter the customer's first and last name.")
                    .showWarning();
            return;
        } else if (editGender.getSelectionModel().getSelectedItem() == null) {
            Dialogs.create()
                    .owner((Stage) editGender.getScene().getWindow())
                    .title("Warning")
                    .masthead("Selection error")
                    .message("Please enter the gender.")
                    .showWarning();
            return;
        } else if (editInsurerId.getSelectionModel().getSelectedItem() == null) {
            Dialogs.create()
                    .owner((Stage) editInsurerId.getScene().getWindow())
                    .title("Warning")
                    .masthead("Selection error")
                    .message("Please enter the insurer.")
                    .showWarning();
            return;
        } else if (editAddress.getText().equals("")) {
            Dialogs.create()
                    .owner((Stage) editAddress.getScene().getWindow())
                    .title("Warning")
                    .masthead("Entry error")
                    .message("Please enter the customer's address.")
                    .showWarning();
            return;
        } else if (editPostalcode.getText().equals("")) {
            Dialogs.create()
                    .owner((Stage) editPostalcode.getScene().getWindow())
                    .title("Warning")
                    .masthead("Entry error")
                    .message("Please enter the customer's postal code.")
                    .showWarning();
            return;
        } else if (editResidence.getText().equals("")) {
            Dialogs.create()
                    .owner((Stage) editResidence.getScene().getWindow())
                    .title("Warning")
                    .masthead("Entry error")
                    .message("Please enter the customer's residence.")
                    .showWarning();
            return;
        } else if (editEmail.getText().equals("")) {
            Dialogs.create()
                    .owner((Stage) editEmail.getScene().getWindow())
                    .title("Warning")
                    .masthead("Entry error")
                    .message("Please enter the customer's email address.")
                    .showWarning();
            return;
        } else if (editTelephone.getText().equals("")) {
            Dialogs.create()
                    .owner((Stage) editTelephone.getScene().getWindow())
                    .title("Warning")
                    .masthead("Entry error")
                    .message("Please enter at least one phone number in the first text box for the customer.")
                    .showWarning();
            return;
        }

        CustomerModel customer = new CustomerModel(MainActivity.editId);
        customer.setFirstname(editFirstname.getText());
        customer.setPrefix(editPrefix.getText());
        customer.setLastname(editLastname.getText());
        customer.setGender(editGender.getSelectionModel().getSelectedItem().toString());
        customer.setInsurerId(Integer.toString(editInsurerId.getSelectionModel().getSelectedItem().getId()));
        customer.setAddress(editAddress.getText());
        customer.setPostalCode(editPostalcode.getText());
        customer.setResidence(editResidence.getText());
        customer.setAddress2(editAddress2.getText());
        customer.setPostalCode2(editPostalcode2.getText());
        customer.setResidence2(editResidence2.getText());
        customer.setEmail(editEmail.getText());
        customer.setTelephone(editTelephone.getText());
        customer.setMobile(editMobile.getText());
        customer.save();

        CustomersController customersController = (CustomersController) StageHelper.callbackController;
        customersController.listOnSearch();

        editCancel();
    }

    /**
     * Creates the (mouse, keyboard, etc.) event filters for the list view.
     */
    @FXML
    private void listKeyActions() {
        listGeneral.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.F1)) {
                listHelp();
            }
        });
        listTableView.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.N)) {
                listNew();
            } else if (b.getCode().equals(KeyCode.H)) {
                listHelp();
            } else if (b.getCode().equals(KeyCode.E)) {
                listEdit();
            } else if (b.getCode().equals(KeyCode.V)) {
                listView();
            } else if (b.getCode().equals(KeyCode.R)) {
                listRemove();
            }
        });
        listSearchField.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE)) {
                listResetTableView("", new String[0]);
                listSearchField.setText("");
                clearNotif();
            }
        });
    }

    /**
     * Creates the event filter for the help view.
     */
    @FXML
    private void helpKeyAction() {

        helpGeneral.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE)) {
                viewClose();
            }
        });
    }

    /**
     * Creates the (mouse, keyboard, etc.) event filters for the new view.
     */
    public void newKeyActions() {
        newFirstname.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE)) {
                newCancel();
            } else if (b.getCode().equals(KeyCode.ENTER)) {
                newSave();
            }
        });
        newPrefix.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE)) {
                newCancel();
            } else if (b.getCode().equals(KeyCode.ENTER)) {
                newSave();
            }
        });
        newLastname.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE)) {
                newCancel();
            } else if (b.getCode().equals(KeyCode.ENTER)) {
                newSave();
            }
        });
        newGender.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE)) {
                newCancel();
            } else if (b.getCode().equals(KeyCode.ENTER)) {
                newGender.show();
            }
        });
        newInsurerId.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE)) {
                newCancel();
            } else if (b.getCode().equals(KeyCode.ENTER)) {
                newInsurerId.show();
            }
        });
        newAddress.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE)) {
                newCancel();
            } else if (b.getCode().equals(KeyCode.ENTER)) {
                newSave();
            }
        });
        newPostalcode.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE)) {
                newCancel();
            } else if (b.getCode().equals(KeyCode.ENTER)) {
                newSave();
            }
        });
        newResidence.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE)) {
                newCancel();
            } else if (b.getCode().equals(KeyCode.ENTER)) {
                newSave();
            }
        });
        newAddress2.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE)) {
                newCancel();
            } else if (b.getCode().equals(KeyCode.ENTER)) {
                newSave();
            }
        });
        newPostalcode2.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE)) {
                newCancel();
            } else if (b.getCode().equals(KeyCode.ENTER)) {
                newSave();
            }
        });
        newResidence2.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE)) {
                newCancel();
            } else if (b.getCode().equals(KeyCode.ENTER)) {
                newSave();
            }
        });
        newEmail.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE)) {
                newCancel();
            } else if (b.getCode().equals(KeyCode.ENTER)) {
                newSave();
            }
        });
        newTelephone.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE)) {
                newCancel();
            } else if (b.getCode().equals(KeyCode.ENTER)) {
                newSave();
            }
        });
        newMobile.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE)) {
                newCancel();
            } else if (b.getCode().equals(KeyCode.ENTER)) {
                newSave();
            }
        });
        newSave.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE)) {
                newCancel();
            } else if (b.getCode().equals(KeyCode.ENTER)) {
                newSave();
            }
        });
        newReset.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE)) {
                newCancel();
            } else if (b.getCode().equals(KeyCode.ENTER)) {
                newReset();
            }
        });
        newCancel.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE) || b.getCode().equals(KeyCode.ENTER)) {
                newCancel();
            }
        });
    }

    /**
     * Creates the (mouse, keyboard, etc.) event filters for the edit view.
     */
    public void editKeyActions() {
        editFirstname.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent evt) -> {
            if (evt.getCode().equals(KeyCode.ESCAPE)) {
                editCancel();
            } else if (evt.getCode().equals(KeyCode.ENTER)) {
                editSave();
            }
        });
        editPrefix.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent evt) -> {
            if (evt.getCode().equals(KeyCode.ESCAPE)) {
                editCancel();
            } else if (evt.getCode().equals(KeyCode.ENTER)) {
                editSave();
            }
        });
        editLastname.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent evt) -> {
            if (evt.getCode().equals(KeyCode.ESCAPE)) {
                editCancel();
            } else if (evt.getCode().equals(KeyCode.ENTER)) {
                editSave();
            }
        });
        editGender.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE)) {
                editCancel();
            } else if (b.getCode().equals(KeyCode.ENTER)) {
                editGender.show();
            }
        });
        editInsurerId.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent evt) -> {
            if (evt.getCode().equals(KeyCode.ESCAPE)) {
                editCancel();
            } else if (evt.getCode().equals(KeyCode.ENTER)) {
                editInsurerId.show();
            }
        });
        editAddress.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent evt) -> {
            if (evt.getCode().equals(KeyCode.ESCAPE)) {
                editCancel();
            } else if (evt.getCode().equals(KeyCode.ENTER)) {
                editSave();
            }
        });
        editPostalcode.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent evt) -> {
            if (evt.getCode().equals(KeyCode.ESCAPE)) {
                editCancel();
            } else if (evt.getCode().equals(KeyCode.ENTER)) {
                editSave();
            }
        });
        editResidence.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent evt) -> {
            if (evt.getCode().equals(KeyCode.ESCAPE)) {
                editCancel();
            } else if (evt.getCode().equals(KeyCode.ENTER)) {
                editSave();
            }
        });
        editAddress2.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent evt) -> {
            if (evt.getCode().equals(KeyCode.ESCAPE)) {
                editCancel();
            } else if (evt.getCode().equals(KeyCode.ENTER)) {
                editSave();
            }
        });
        editPostalcode2.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent evt) -> {
            if (evt.getCode().equals(KeyCode.ESCAPE)) {
                editCancel();
            } else if (evt.getCode().equals(KeyCode.ENTER)) {
                editSave();
            }
        });
        editResidence2.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent evt) -> {
            if (evt.getCode().equals(KeyCode.ESCAPE)) {
                editCancel();
            } else if (evt.getCode().equals(KeyCode.ENTER)) {
                editSave();
            }
        });
        editEmail.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent evt) -> {
            if (evt.getCode().equals(KeyCode.ESCAPE)) {
                editCancel();
            } else if (evt.getCode().equals(KeyCode.ENTER)) {
                editSave();
            }
        });
        editTelephone.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent evt) -> {
            if (evt.getCode().equals(KeyCode.ESCAPE)) {
                editCancel();
            } else if (evt.getCode().equals(KeyCode.ENTER)) {
                editSave();
            }
        });
        editMobile.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent evt) -> {
            if (evt.getCode().equals(KeyCode.ESCAPE)) {
                editCancel();
            } else if (evt.getCode().equals(KeyCode.ENTER)) {
                editSave();
            }
        });
        editSave.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent evt) -> {
            if (evt.getCode().equals(KeyCode.ESCAPE)) {
                editCancel();
            } else if (evt.getCode().equals(KeyCode.ENTER)) {
                editSave();
            }
        });
        editReset.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent evt) -> {
            if (evt.getCode().equals(KeyCode.ESCAPE)) {
                editCancel();
            } else if (evt.getCode().equals(KeyCode.ENTER)) {
                editReset();
            }
        });
        editCancel.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent evt) -> {
            if (evt.getCode().equals(KeyCode.ESCAPE) || evt.getCode().equals(KeyCode.ENTER)) {
                editCancel();
            }
        });
    }

    /**
     * Creates the (mouse, keyboard, etc.) event filters for the view page.
     */
    public void viewKeyActions() {
        viewFirstname.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE) || b.getCode().equals(KeyCode.ENTER)) {
                viewClose();
            }
        });
        viewPrefix.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE) || b.getCode().equals(KeyCode.ENTER)) {
                viewClose();
            }
        });
        viewLastname.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE) || b.getCode().equals(KeyCode.ENTER)) {
                viewClose();
            }
        });
        viewGenderAsText.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE) || b.getCode().equals(KeyCode.ENTER)) {
                viewClose();
            }
        });
        viewInsurerAsText.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE) || b.getCode().equals(KeyCode.ENTER)) {
                viewClose();
            }
        });
        viewAddress.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE) || b.getCode().equals(KeyCode.ENTER)) {
                viewClose();
            }
        });
        viewPostalcode.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE) || b.getCode().equals(KeyCode.ENTER)) {
                viewClose();
            }
        });
        viewResidence.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE) || b.getCode().equals(KeyCode.ENTER)) {
                viewClose();
            }
        });
        viewAddress2.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE) || b.getCode().equals(KeyCode.ENTER)) {
                viewClose();
            }
        });
        viewPostalcode2.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE) || b.getCode().equals(KeyCode.ENTER)) {
                viewClose();
            }
        });
        viewResidence2.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE) || b.getCode().equals(KeyCode.ENTER)) {
                viewClose();
            }
        });
        viewEmail.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE) || b.getCode().equals(KeyCode.ENTER)) {
                viewClose();
            }
        });
        viewTelephone.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE) || b.getCode().equals(KeyCode.ENTER)) {
                viewClose();
            }
        });
        viewMobile.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE) || b.getCode().equals(KeyCode.ENTER)) {
                viewClose();
            }
        });
        viewClose.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE) || b.getCode().equals(KeyCode.ENTER)) {
                viewClose();
            }
        });
    }

    /**
     * Prints given parameter as notification label.
     *
     * @param notif
     */
    @FXML
    private void printNotif(String notif) {
        printNotif.setText(notif);
    }

    /**
     * Clears the notification label.
     */
    @FXML
    private void clearNotif() {
        printNotif.setText("");
    }

    /**
     * Clears the notification label.
     */
    @FXML
    private void clearSearch() {
        listOnSearch();
        clearNotif();
    }

    /**
     * Closes current view.
     */
    public void viewClose() {
        Stage cancelStage = (Stage) viewClose.getScene().getWindow();
        StageHelper.closeStage(cancelStage);
    }
}
