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
 */
package luggage;

import luggage.database.DatabaseHelper;
import luggage.helpers.StageHelper;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * MainActivity
 *
 * Main activity for the application
 *
 * @package luggage
 * @author Tijme Gommers
 */
public class MainActivity extends Application {

    /**
     * Called on application run
     * 
     * @param primaryStage
     * @throws Exception 
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        StageHelper.addStage("login", this.getClass());
        
        (new Thread(() -> {
            DatabaseHelper.openConnection();
        })).start();
        
        /*
        UserModel tijme = new UserModel();
        tijme.setFirstname("Tijme");
        tijme.setMiddlename("");
        tijme.setLastname("Gommers");
        tijme.save();
        
        UserModel tijme = new UserModel(1);
        tijme.setFirstname("Tijme");
        tijme.save();
        
        UserModel tijme = new UserModel(1);
        tijme.delete();
        */
    }
    
    /**
     * Called on application run
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
