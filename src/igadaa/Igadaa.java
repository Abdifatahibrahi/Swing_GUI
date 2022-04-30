/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package igadaa;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import static java.awt.SystemColor.text;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;
import java.util.EventObject;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Abdelfatah Ibrahim
 */
public class Igadaa {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       igadaa1 obj = new igadaa1();
    }
    
}

class igadaa1 extends JFrame{
    private panel pnl;
    private JButton btn;
    private Toolbar tb;
    private Formpanel fp;
    private FormListener listener;
    
    
  public igadaa1(){
        fp = new Formpanel();
        tb = new Toolbar();
        btn = new JButton("ok");
        pnl = new panel();
        
        add(fp);
        add(tb);
//        add(btn);
        add(pnl);
      
        
        fp.setFormListener(new FormListener() {
            @Override
            public void EventOccured(FormEvent e) {
                
              String name = e.getName();
              String occupation = e.getOccupation();
              
              pnl.setText(name+ ":" + occupation + "\n");
                
            }
        });
        
        
        
//        tb.setTextPanel(pnl);

//        tb.setStringListener(new StringListener() {
//            @Override
//            public void permission(String text) {
//                
//                pnl.setText(text);
//                
//              }
//        });
    
        
//        btn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                
//                pnl.setText("Hello");
//            }
//        });
        setLayout(new FlowLayout());        
        setVisible(true);        
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}

class panel extends JPanel{
    private JTextArea textarea;
    
    public panel(){
        setLayout(new FlowLayout());
        textarea = new JTextArea(10,10);
        add(textarea);
        
    }
    public void setText(String text){
       textarea.append(text);
    }
}

class Toolbar extends JPanel implements ActionListener{
    private JButton btn1,btn2;
    private panel pnl;
    private StringListener listener;
    
    public Toolbar(){
        
        pnl = new panel();
        btn1 = new JButton("Hello");
        btn2 = new JButton("Good Morning");
        
//        add(pnl);
//        add(btn1);
//        add(btn2);
        
        btn1.addActionListener(this);
        btn2.addActionListener(this);


    }
    public void actionPerformed(ActionEvent e){
        JButton clicked = (JButton) e.getSource();
        if(clicked == btn1){
//            pnl.setText("Hello");
            if(listener != null){
                listener.permission("Hello");
            }
        }
        if(clicked == btn2){
//            pnl.setText("Good Morning");
               if(listener != null){
                listener.permission("Good Morning");
            }
        }
        
    }
    public void setTextPanel(panel pnl){
        this.pnl = pnl;
    }  
    public void setFormListener(FormListener listener){
        this.listener = (StringListener) listener;
    }
            
}

interface StringListener{
    public void permission(String text);
}


class Formpanel extends JPanel{
    private JLabel l1,l2;
    private JTextField f1,f2;
    private JButton btn;
    private FormListener listener;
    private JList list;
    private JCheckBox boxes;
    private JComboBox box;
    
    public Formpanel(){
        l1 = new JLabel("Name:");
        l2 = new JLabel("Occupation:");
        f1 = new JTextField(10);
        f2 = new JTextField(10);
        btn = new JButton("OK");
        list = new JList();
        boxes = new JCheckBox();
        box = new JComboBox();
        
        
//        DefaultCheckBoxModel checkmodel = new DefaultCheckBoxModel();
        DefaultComboBoxModel cbox = new DefaultComboBoxModel();
        cbox.addElement("Good");
        cbox.addElement("Good");
        cbox.addElement("Good");
        cbox.addElement("Good");
        box.setModel(cbox);
        
        add(box);
        
        DefaultListModel model = new DefaultListModel();
        model.addElement(new AgeCategory(0,"A"));
        model.addElement(new AgeCategory(0,"B"));
        model.addElement(new AgeCategory(0,"C"));
        model.addElement(new AgeCategory(0,"D"));
        model.addElement(new AgeCategory(0,"E"));
        
        list.setModel(model);
        
        add(list);
        add(btn);
        add(l1);
        add(l2);
        add(f1);
        add(f2);
        
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = f1.getText();
                String occupation = f2.getText();
                AgeCategory Agecat = (AgeCategory) list.getSelectedValue();
                
                System.out.println(Agecat.getId());
                FormEvent ev = new FormEvent(this,name,occupation);
                
               if(listener != null){
                   listener.EventOccured(ev);
               }

            }
        });
        
        
        setLayout(new GridBagLayout());
        
        GridBagConstraints gc = new GridBagConstraints();
        
        gc.weightx = 3;
        gc.weighty = 3;
        
//        gc.fill = GridBagConstraints.NONE;
        gc.gridx = 0;
        gc.gridy = 0;
        add(l1);
        
        gc.gridx = 1;
        gc.gridy = 0;
        add(f1);
        
        gc.gridx = 0;
        gc.gridy = 1;
        add(l1);
        
        gc.gridx = 1;
        gc.gridy = 1;
        add(f2);
        
    }
    
    public void setFormListener(FormListener listener){
            
            this.listener = listener;
            
        }
}

class AgeCategory {
    private int Id;
    private String text;
    public AgeCategory(int Id,String name){
        this.Id = Id;
        this.text = text;
    }
    
    public String toString(){
        return text;
    }
    
    public int getId(){
        return Id;
    }
}

class FormEvent extends EventObject{
   private String name;
    private String occupation;
    public FormEvent(Object source) {
        super(source);
    }
    
    public FormEvent(Object source, String name,String occupation) {
        super(source);
        
        this.name = name;
        this.occupation = occupation;
    
}
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getOccupation(){
        return occupation;
    }
    
    public void setOccupation(String name){
        this.name = occupation;
    }
    
    
    
}

interface FormListener extends EventListener {
    public void EventOccured(FormEvent e);
}