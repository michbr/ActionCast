package action_cast.widgets;

import action_cast.controller.ClientObjects.Person;
import action_cast.model.Role;
import action_cast.widgets.custom.JTileView;
import action_cast.widgets.tiles.RoleTile;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by bmichaud on 12/14/2015.
 */
public class RoleAssignmentGrid extends JTileView {

    private Map<Person, Role> assignments;

    public void setData(Map<Person, Role> assignments) {
        this.assignments = assignments;
        updateDisplay();
    }

    public void updateDisplay() {
        Set<Map.Entry<Person, Role>> entries = assignments.entrySet();
        Iterator<Map.Entry<Person, Role>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<Person, Role> entry = iterator.next();
            super.add(new RoleTile(this, entry.getKey(), entry.getValue()));
        }
        //int size = assignments.values().size();
       // size =(int)Math.round(Math.ceil(Math.sqrt(size)));
        /*for (int row = 0; row < size/2; row++) {
            for (int col = 0; col < size/2; col++) {
                int index = getColumnCount() * row + col;
                if (index < assignments.values().size()) {
                    ((UneditableTableModel) getModel()).setValueAt(assignments.v, row, col);
                }
                else {
                    ((UneditableTableModel) getModel()).setValueAt("", row, col);
                }
            }
        }*/
    }

}
