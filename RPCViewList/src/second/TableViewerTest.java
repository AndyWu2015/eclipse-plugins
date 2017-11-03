package second;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.part.ViewPart;

public class TableViewerTest extends ViewPart {
    public static final String ID = "second.TableViewerTest";
    private TableViewer viewer;
    public TableViewerTest() {
        // TODO Auto-generated constructor stub
    }

    public void createPartControl(Composite parent) {
        viewer = new TableViewer(parent,SWT.SINGLE|SWT.FULL_SELECTION);
        Table table = viewer.getTable();
        table.setHeaderVisible(true);
        table.setLinesVisible(true);
        
        String[] columnNames = new String[]{"id","name","age","address"};
        int[] columnWidths = new int[]{100,100,100,100};
        int[] columnAlignments = new int[]{SWT.LEFT,SWT.LEFT,SWT.LEFT,SWT.LEFT};
        for(int i=0;i<columnNames.length;i++){
            TableColumn tableColumn  = new TableColumn(table,columnAlignments[i]);
            tableColumn.setText(columnNames[i]);
            tableColumn.setWidth(columnWidths[i]);
        }
        int columnCount = table.getColumnCount(); 
        TableColumn[] columns = table.getColumns();
        for(int i=0; i <columnCount ; i++ ){
        	System.out.println(table.getColumn(i).getText());
        }
        
        viewer.setContentProvider(new ViewContentProvider());
        viewer.setLabelProvider(new ViewLabelProvider());
        viewer.setSorter(new MySorter());
        viewer.setInput(getSite());
    }

    public void setFocus() {
        // TODO Auto-generated method stub

    }
    class ViewContentProvider implements IStructuredContentProvider {
        public Object[] getElements(Object inputElement) {
            
            Person[] persons = new Person[3];
            persons[0] = new Person();
            persons[0].setId(001);
            persons[0].setName("xingoo");
            persons[0].setAge(25);
            persons[0].setAddress("ChangChun");
            
            persons[1] = new Person();
            persons[1].setId(002);
            persons[1].setName("halo");
            persons[1].setAge(27);
            persons[1].setAddress("ShenYang");
            
            persons[2] = new Person();
            persons[2].setId(003);
            persons[2].setName("haha");
            persons[2].setAge(25);
            persons[2].setAddress("DaLian");
            
            return persons;
        }
        public void dispose() {
        }
        public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        }
    }
    class ViewLabelProvider extends LabelProvider implements ITableLabelProvider{
        public Image getColumnImage(Object element,int index) {
            return null;
        }
        public String getColumnText(Object element,int index) {
            Person person = (Person)element;
            switch(index){
            case 0:
                return person.getId()+"";
            case 1:
                return person.getName();
            case 2:
                return person.getAge()+"";
            case 3:
                return person.getAddress();
            default:
                return "unknown"+index;
            }
        }
    }
    class MySorter extends ViewerSorter{
        public int compare(Viewer viewer,Object ob1,Object ob2){
            return ((Person)ob1).getId() - ((Person)ob2).getId();
        }
    }

}
