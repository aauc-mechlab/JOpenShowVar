/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hials.crosscom.swing;

import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.FilterList;
import ca.odell.glazedlists.SortedList;
import ca.odell.glazedlists.matchers.MatcherEditor;
import ca.odell.glazedlists.swing.AdvancedTableModel;
import ca.odell.glazedlists.swing.DefaultEventSelectionModel;
import ca.odell.glazedlists.swing.GlazedListsSwing;
import ca.odell.glazedlists.swing.TableComparatorChooser;
import ca.odell.glazedlists.swing.TextComponentMatcherEditor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import no.hials.crosscom.variables.Variable;

/**
 *
 * @author LarsIvar
 */
public class VarBrowser extends JPanel {

    private final EventList<Variable> eventList;
    private final JTable table;

    public VarBrowser(final VarModel model, final EditPanel editPanel) {
        this.eventList = model.getVariables();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        SortedList<Variable> sortedIssues = new SortedList<>(eventList, new VarComparator());
        JTextField filterEdit = new JTextField(10);
        filterEdit.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));
        MatcherEditor<Variable> textMatcherEditor = new TextComponentMatcherEditor<>(filterEdit, new VarTextFilterator());
        FilterList<Variable> textFilteredVariables = new FilterList<>(sortedIssues, textMatcherEditor);

        AdvancedTableModel<Variable> varTableModel = GlazedListsSwing.eventTableModelWithThreadProxyList(textFilteredVariables, new VarTableFormat());
        table = new JTable(varTableModel);
        TableComparatorChooser<Variable> tableSorter = TableComparatorChooser.install(table, sortedIssues, TableComparatorChooser.MULTIPLE_COLUMN_MOUSE);
        JScrollPane issuesTableScrollPane = new JScrollPane(table);

        final DefaultEventSelectionModel<Variable> selectionModel = new DefaultEventSelectionModel<>(textFilteredVariables);
        table.setSelectionModel(selectionModel);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int r = table.rowAtPoint(e.getPoint());
                if (r >= 0 && r < table.getRowCount()) {
                    table.setRowSelectionInterval(r, r);
                } else {
                    table.clearSelection();
                }

                int rowindex = table.getSelectedRow();
                if (rowindex < 0) {
                    return;
                }
                if (e.isPopupTrigger() && e.getComponent() instanceof JTable) {
                    JPopupMenu popup = createPopUp();
                    popup.show(e.getComponent(), e.getX(), e.getY());
                }
            }

            private JPopupMenu createPopUp() {
                 JPopupMenu popupMenu = new JPopupMenu();
                 
                 final JMenuItem editMenuItem = new JMenuItem("Edit");
                 editMenuItem.addActionListener((ActionEvent e) -> {
                     if (!selectionModel.isSelectionEmpty()) {
                         editPanel.addVariable(selectionModel.getSelected().get(0));
                     }
                 });
                 
                final JMenuItem removeMenuItem = new JMenuItem("Remove");
                removeMenuItem.addActionListener((ActionEvent e) -> {
                    if (!selectionModel.isSelectionEmpty()) {
                        eventList.removeAll(selectionModel.getSelected());
                    }
                 });
               
                popupMenu.add(editMenuItem);
                popupMenu.add(removeMenuItem);
                return popupMenu;
            }
        });

        final JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        filterPanel.add(new JLabel("Filter: "));
        filterPanel.add(filterEdit);
        this.add(filterPanel);
        this.add(issuesTableScrollPane);
    }

    public JTable getTable() {
        return table;
    } 
}
