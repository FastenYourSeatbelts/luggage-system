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
package luggage.database.models;

/**
 * Insurer Model
 *
 * A class that maps the insurers table
 *
 * Package: luggage.database.models
 * @author ITopia IS102-5
 */
public class InsurerModel extends Model {

    /**
     *
     */
    public InsurerModel() {

    }

    /**
     *
     * @param id
     */
    public InsurerModel(int id) {
        super(id);
    }

    /**
     *
     * @param where
     * @param params
     */
    public InsurerModel(String where, String... params) {
        super(where, params);
    }

    /**
     *
     * @return insurermodel
     */
    @Override
    protected Model getModel() {
        return new InsurerModel();
    }

    /**
     *
     * @return insurers
     */
    @Override
    protected String getTable() {
        return "insurers";
    }

    /**
     *
     * @return standard order by
     */
    @Override
    protected String getOrderBy() {
        return "ORDER BY name ASC";
    }

    /**
     *
     * @return name
     */
    @Override
    public String toString() {
        return getName();
    }

    /**
     * Return the name of the current row
     *
     * @return
     */
    public String getName() {
        return row.get("name");
    }

    /**
     * Set the name of the current row
     *
     * @param name
     */
    public void setName(String name) {
        row.put("name", name);
    }

}
