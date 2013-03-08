package safari.structure;

import java.awt.Component;
import java.awt.Point;
import java.util.ArrayList;

public class CompNode {

        private CompNode parent;
        private ArrayList<CompNode> children;
        private Component value;
        private Point gridLoc = new Point(-1, -1);
        private String borderLoc = "";
        private Point gridSpan = new Point(-1, -1);

        public CompNode(Component value) {
                this.value = value;
                this.parent = null;
                this.children = new ArrayList<CompNode>();
        }

        public String getBorderLoc(){return this.borderLoc;}

        public Component getValue(){return value;}

        public CompNode getDirectParent(){return parent;}

        public Point getGridLoc(){return this.gridLoc;}
        
        public Point getGridSpan(){return this.gridSpan;}

        public void addChild(Component comp){
                CompNode cn = new CompNode(comp);
                cn.setParent(this);
                this.children.add(cn);
        }

        public void setParent(CompNode cn){this.parent = cn;}
        public void setGridLoc(int x, int y){gridLoc.setLocation(x, y);}
        public void setGridSpan(int rows, int cols){this.gridSpan.setLocation(rows, cols);}
        public void setBorderLoc(String specifier){this.borderLoc = specifier;}


}