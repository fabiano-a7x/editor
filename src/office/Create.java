/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package office;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.JViewport;
import javax.swing.KeyStroke;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.plaf.ComponentUI;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;



/**
 *
 * @author fabiano
 */
public class Create extends javax.swing.JFrame {    
     private Color colorSelect = Color.BLACK;
     private String nomeFont = "Arial";
     private  StyleContext context = new StyleContext();
     private  StyledDocument document = new DefaultStyledDocument(context);
     private boolean bold = false;
     private boolean under = false;
     private boolean italic = false;
     
    /**
     * Creates new form Create
     */
    int size=14, style=4;
    public Create() {
        initComponents();           
        GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();        
        final Font[] fonts = e.getAllFonts();
        for(int i=0; i<fonts.length; i++){
            cbFont.addItem(fonts[i].getFontName());
        }
        
        int i = 5;
        while(i < 90){            
            if (i <=20) {
                i++;
            }else if(i < 40){
                i += 4;
            }else if(i < 70){
                i+=6;
            }else{
                i+=8;
            }
            cbFontSize.addItem(i);
        }
        btnNegrito.setMnemonic('n'); 
        cbFontSize.setSelectedIndex(12);
        cbFont.setSelectedIndex(2);
        tpAbas2.add(plAbas);
        
        this.setSize(650, 700); 
        tpAbas2.setSize(640, 690);
        tpAbas2.addTab("+", new JLabel());
        tpAbas2.setTabComponentAt(tpAbas2.indexOfComponent(plAbas), getTitlePanel(tpAbas2, plAbas, "arquivo"+(tpAbas2.getTabCount()-1)));
                 
                final UndoManager undo = new UndoManager();                
                Document doc = getEditor().getDocument();;                
                // Ouve eventos de desfazer e refazer
                doc.addUndoableEditListener(new UndoableEditListener() {
                    public void undoableEditHappened(UndoableEditEvent evt) {
                       undo.addEdit(evt.getEdit());                                               
                    }
                });
                   lblDesfazer.addActionListener(
                   new ActionListener(){
                      public void actionPerformed(ActionEvent e){
                        try{
                           
                           if(undo.canUndo()){                               
                               undo.undo();
                              
                           }
                        } 
                        catch(CannotUndoException cue){
                           // possiveis erros são tratados aqui 
                        }
                      }
                   });
                   lblRefazer.addActionListener(
                   new ActionListener(){
                      public void actionPerformed(ActionEvent e){
                        try{
                           
                           if(undo.canRedo()){
                             undo.redo();
                           }
                        } 
                        catch(CannotUndoException cue){
                           // possiveis erros são tratados aqui 
                        }
                      }
                   });

        tpAbas2.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            iniciarTab(0);
        }

    }); 
    
}
    public void iniciarTab(int i){
        
        if (tpAbas2.getSelectedComponent() instanceof JLabel|| i == 1) {                            
                int count = tpAbas2.getTabCount();
                
                if (count < 11) {
                    tpAbas2.removeTabAt(count-1);
                }
                if (count > 10) {
                    JOptionPane.showMessageDialog(rootPane, "No máximo 10 abas!");
                }else{                                       
                   
                    
                    
                    JPanel panel = new JPanel();
                    panel.setOpaque(false);       
                    final JTextPane pane = new JTextPane();                    
                                                                                                  
                    
                    panel.add(pane);
                    
                    JScrollPane jScrollPane3 = new JScrollPane(pane); 
                    jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                    jScrollPane3.setViewportView(pane);
                    javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
                    panel.setLayout(panelLayout);
                    panelLayout.setHorizontalGroup(
                        panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 543, Short.MAX_VALUE)
                            .addContainerGap())
                    );
                    panelLayout.setVerticalGroup(
                        panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
                            .addContainerGap())
                    );

                  
                    getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));
                    tpAbas2.add(panel);
                    tpAbas2.setTabComponentAt(tpAbas2.indexOfComponent(panel), getTitlePanel(tpAbas2, panel, "arquivo"+tpAbas2.getTabCount()));
                    
                    tpAbas2.addTab("+", new JLabel());
                    tpAbas2.setSelectedIndex(tpAbas2.getTabCount()-2);
                    pane.addKeyListener(new KeyListener() {

                        @Override
                        public void keyTyped(KeyEvent e) {
                                                     
                        }                               
                        @Override
                        public void keyReleased(KeyEvent e) {
                            Create.this.keyReleased(e);  
                        }
                         @Override
                        public void keyPressed(KeyEvent evt  ) {
                            Create.this.keyPressed(evt);
                        }

                    });
                final UndoManager[] undo = new UndoManager[10];               
                undo[tpAbas2.getSelectedIndex()] = new UndoManager();                
                final Document[] doc = new Document[10];
                doc[tpAbas2.getSelectedIndex()] = getEditor().getDocument();

                // Ouve eventos de desfazer e refazer
                doc[tpAbas2.getSelectedIndex()].addUndoableEditListener(new UndoableEditListener() {
                    public void undoableEditHappened(UndoableEditEvent evt) {
                       undo[tpAbas2.getSelectedIndex()].addEdit(evt.getEdit());                                               
                    }
                });
                   lblDesfazer.addActionListener(
                   new ActionListener(){
                      public void actionPerformed(ActionEvent e){
                        try{
                           if (undo[tpAbas2.getSelectedIndex()] == null) {
                                undo[tpAbas2.getSelectedIndex()] = new UndoManager();  
                           }
                           if(undo[tpAbas2.getSelectedIndex()].canUndo()){                               
                               undo[tpAbas2.getSelectedIndex()].undo();
                              
                           }
                        } 
                        catch(CannotUndoException cue){
                           // possiveis erros são tratados aqui 
                        }
                      }
                   });
                   lblRefazer.addActionListener(
                   new ActionListener(){
                      public void actionPerformed(ActionEvent e){
                        try{
                           if (undo[tpAbas2.getSelectedIndex()] == null) {
                                undo[tpAbas2.getSelectedIndex()] = new UndoManager();  
                           }
                           if(undo[tpAbas2.getSelectedIndex()].canRedo()){
                             undo[tpAbas2.getSelectedIndex()].redo();
                           }
                        } 
                        catch(CannotUndoException cue){
                           // possiveis erros são tratados aqui 
                        }
                      }
                   });
                }
            }
    }
    
   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        plAbas = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tpEditor = new javax.swing.JTextPane();
        jPanel2 = new javax.swing.JPanel();
        jToolBar2 = new javax.swing.JToolBar();
        btn = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        jButton2 = new javax.swing.JButton();
        jSeparator11 = new javax.swing.JToolBar.Separator();
        btnSalvar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        lblDesfazer = new javax.swing.JButton();
        jSeparator9 = new javax.swing.JToolBar.Separator();
        lblRefazer = new javax.swing.JButton();
        jSeparator10 = new javax.swing.JToolBar.Separator();
        cbFont = new javax.swing.JComboBox();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        cbFontSize = new javax.swing.JComboBox();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        jButton1 = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        btnNegrito = new javax.swing.JToggleButton();
        jSeparator6 = new javax.swing.JToolBar.Separator();
        btnItalico = new javax.swing.JToggleButton();
        jSeparator7 = new javax.swing.JToolBar.Separator();
        btnSublinhado = new javax.swing.JToggleButton();
        jSeparator8 = new javax.swing.JToolBar.Separator();
        tpAbas2 = new javax.swing.JTabbedPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        mtNovo = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        plAbas.setBackground(new java.awt.Color(234, 234, 234));

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setViewportBorder(javax.swing.BorderFactory.createCompoundBorder());

        tpEditor.setMaximumSize(new java.awt.Dimension(300, 300));
        tpEditor.setSelectedTextColor(new java.awt.Color(3, 3, 3));
        tpEditor.setSelectionColor(new java.awt.Color(163, 246, 213));
        tpEditor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tpEditorKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tpEditorKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tpEditor);

        javax.swing.GroupLayout plAbasLayout = new javax.swing.GroupLayout(plAbas);
        plAbas.setLayout(plAbasLayout);
        plAbasLayout.setHorizontalGroup(
            plAbasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, plAbasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 543, Short.MAX_VALUE)
                .addContainerGap())
        );
        plAbasLayout.setVerticalGroup(
            plAbasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, plAbasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(750, 300));
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jPanel2.setBackground(new java.awt.Color(226, 231, 233));
        jPanel2.setMaximumSize(null);

        jToolBar2.setBackground(java.awt.Color.white);
        jToolBar2.setFloatable(false);
        jToolBar2.setRollover(true);

        btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ic_open.png"))); // NOI18N
        btn.setFocusable(false);
        btn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActionPerformed(evt);
            }
        });
        jToolBar2.add(btn);
        jToolBar2.add(jSeparator5);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/New File-32.png"))); // NOI18N
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jToolBar2.add(jButton2);
        jToolBar2.add(jSeparator11);

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ic_save.png"))); // NOI18N
        btnSalvar.setToolTipText("salvar");
        btnSalvar.setFocusable(false);
        btnSalvar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSalvar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });
        jToolBar2.add(btnSalvar);
        jToolBar2.add(jSeparator1);

        lblDesfazer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ic_undo_icon.png"))); // NOI18N
        lblDesfazer.setFocusable(false);
        lblDesfazer.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblDesfazer.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar2.add(lblDesfazer);
        jToolBar2.add(jSeparator9);

        lblRefazer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ic_redo_icon.png"))); // NOI18N
        lblRefazer.setFocusable(false);
        lblRefazer.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblRefazer.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar2.add(lblRefazer);
        jToolBar2.add(jSeparator10);

        cbFont.setToolTipText("família da fonte");
        cbFont.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbFontActionPerformed(evt);
            }
        });
        jToolBar2.add(cbFont);
        jToolBar2.add(jSeparator2);

        cbFontSize.setToolTipText("Tamanho da fonte");
        cbFontSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbFontSizeActionPerformed(evt);
            }
        });
        jToolBar2.add(cbFontSize);

        jSeparator3.setMaximumSize(new java.awt.Dimension(12, 3));
        jSeparator3.setMinimumSize(new java.awt.Dimension(12, 8));
        jToolBar2.add(jSeparator3);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Color picker-32.png"))); // NOI18N
        jButton1.setMnemonic('w');
        jButton1.setToolTipText("Selecione uma cor");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar2.add(jButton1);
        jToolBar2.add(jSeparator4);

        btnNegrito.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        btnNegrito.setText("<html><b>A</b></html>");
        btnNegrito.setFocusable(false);
        btnNegrito.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNegrito.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnNegrito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNegritoActionPerformed(evt);
            }
        });
        jToolBar2.add(btnNegrito);
        jToolBar2.add(jSeparator6);

        btnItalico.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        btnItalico.setText("<html><i>A</i></html>");
        btnItalico.setFocusable(false);
        btnItalico.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnItalico.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnItalico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnItalicoActionPerformed(evt);
            }
        });
        jToolBar2.add(btnItalico);
        jToolBar2.add(jSeparator7);

        btnSublinhado.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        btnSublinhado.setText("<html><u>A</u></html>");
        btnSublinhado.setFocusable(false);
        btnSublinhado.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSublinhado.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSublinhado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSublinhadoActionPerformed(evt);
            }
        });
        jToolBar2.add(btnSublinhado);
        jToolBar2.add(jSeparator8);

        tpAbas2.setBackground(new java.awt.Color(7, 2, 2));
        tpAbas2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar2, javax.swing.GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tpAbas2)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tpAbas2, javax.swing.GroupLayout.DEFAULT_SIZE, 608, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel2);

        jMenu1.setText("arquivo");

        mtNovo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        mtNovo.setText("Novo");
        mtNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mtNovoActionPerformed(evt);
            }
        });
        jMenu1.add(mtNovo);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Abrir");
        jMenu1.add(jMenuItem2);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setText("Salvar");
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Ajuda");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Sobre");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public void setColor(Color color,java.awt.event.ActionEvent evt){       
       tpEditor.setForeground(Color.ORANGE);        
       colorSelect = color;        
    }
   
    private void cbFontActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbFontActionPerformed
        nomeFont = cbFont.getSelectedItem().toString();
        try{
            styleFont(bold,under,italic,nomeFont,colorSelect,size,1);   
        }catch(ArrayIndexOutOfBoundsException e){
            
        }        
    }//GEN-LAST:event_cbFontActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JColorChooser jc = new JColorChooser();                                   
        colorSelect = jc.showDialog(this,"Selecione uma cor:",colorSelect);        
        styleFont(bold,under,italic,nomeFont,colorSelect,size,1);
    }//GEN-LAST:event_jButton1ActionPerformed
    public String colorHexString(Color color) {
        return "#" + Integer.toHexString(color.getRGB() & 0x00ffffff);
    }
    public void styleFont(boolean bold, boolean under, boolean italic,String fontFamaly,Color color, int size,int tipo){        
        JTextPane tp = getEditor();
        String textSelected = null;
        int count = 0;
        int start = 0;
        int end = 0;
        try{
            end = tp.getSelectionEnd();
            start = tp.getSelectionStart();        
            count = tp.getSelectedText().length();
            textSelected= tp.getSelectedText();            
        }catch(NullPointerException e){
            
        }
        
               
        SimpleAttributeSet attributes = new SimpleAttributeSet();                       
        StyleConstants.setBold(attributes, bold);
        StyleConstants.setUnderline(attributes, under);  
        StyleConstants.setItalic(attributes, italic);
        StyleConstants.setFirstLineIndent(attributes, 400);
        if(tipo == 1){
            StyleConstants.setFontFamily(attributes, fontFamaly);
            StyleConstants.setForeground(attributes,color);
            StyleConstants.setFontSize(attributes, size);         
        }       
        if (textSelected != null) {            
            try {                                           
                tp.getStyledDocument().remove(start,count);              
                
            } catch (BadLocationException ex) {
                Logger.getLogger(Create.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                 tp.getStyledDocument().insertString(start,textSelected , attributes);
                 tp.select(start, end);
                 tp.setSelectedTextColor(color);                                  
            } catch (BadLocationException ex) {
                 Logger.getLogger(Create.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{            
            try {
                 tp.getStyledDocument().insertString(tp.getStyledDocument().getLength()," " , attributes);
            } catch (BadLocationException ex) {
                 Logger.getLogger(Create.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
    }
    private void cbFontSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbFontSizeActionPerformed
        size = (int) cbFontSize.getSelectedItem();
        try{
            styleFont(bold,under,italic,nomeFont,colorSelect,size,1);   
        }catch(ArrayIndexOutOfBoundsException e){
            
        } 
    }//GEN-LAST:event_cbFontSizeActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        JFileChooser fc = new JFileChooser();
        fc.showSaveDialog(this);        
        FileWriter txt;
        try{
            
            txt = new FileWriter(new File(fc.getSelectedFile().getAbsolutePath()+".txt"));
            tpEditor = getEditor();
            txt.write(tpEditor.getText());
            txt.close();
            JOptionPane.showMessageDialog(this,"Salvo com sucesso!");
        }catch(Exception e){
            JOptionPane.showMessageDialog(this,e.getMessage()+" erro!");
        }
        
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActionPerformed
       open();
    }//GEN-LAST:event_btnActionPerformed
    public void open(){
         JFileChooser fc = new JFileChooser();
        fc.showOpenDialog(this);
        String txt = "";
        String str = "";        
         try {
             BufferedReader in = new BufferedReader(new FileReader(fc.getSelectedFile()));
             while((str = in.readLine()) != null){
                 txt += str + "\n";                 
             }
            in.close();
            tpEditor = getEditor();
            tpEditor.setText(txt);
         } catch (FileNotFoundException ex) {
             Logger.getLogger(Create.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException ex) {
             Logger.getLogger(Create.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    public JTextPane getEditor(){
        JPanel p =  (JPanel) tpAbas2.getComponentAt(tpAbas2.getSelectedIndex());
        Component[] x = p.getComponents();
        JScrollPane scroll =  (JScrollPane) x[0];
        JViewport port = (JViewport) scroll.getComponent(0);
        return (JTextPane) port.getComponent(0);                           
    }
    private void btnNegritoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNegritoActionPerformed
    
        if (btnNegrito.isSelected()) {            
            bold = true;
            styleFont(bold,under,italic,nomeFont,colorSelect,size,2);
        }else{
            bold = false;
            styleFont(bold,under,italic,nomeFont,colorSelect,size,2);            
        }                         
    }//GEN-LAST:event_btnNegritoActionPerformed

    private void btnItalicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnItalicoActionPerformed
        if (btnItalico.isSelected()) {            
            italic = true;
            styleFont(bold,under,italic,nomeFont,colorSelect,size,2);
        }else{
            italic = true;
            styleFont(bold,under,italic,nomeFont,colorSelect,size,2);            
        }
            
    }//GEN-LAST:event_btnItalicoActionPerformed
   
    private void btnSublinhadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSublinhadoActionPerformed
       if(btnSublinhado.isSelected()){
           under = true;
           styleFont(bold,under,italic,nomeFont,colorSelect,size,2);
       }else{
           under = false;
           styleFont(bold,under,italic,nomeFont,colorSelect,size,3);
       }
    }//GEN-LAST:event_btnSublinhadoActionPerformed
    int last = 0;
    private void tpEditorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tpEditorKeyPressed
        keyPressed(evt);
    }//GEN-LAST:event_tpEditorKeyPressed
    public void keyPressed(java.awt.event.KeyEvent evt) { 
        JTextPane tp = getEditor();       
        if (evt.getKeyCode() == 9) {
            String lastLetter = null;
            try{
                lastLetter = Character.toString(tp.getText().charAt(tp.getText().length()-1));
            }catch(StringIndexOutOfBoundsException e){
                
            }catch(NullPointerException ke){
            
            }            
            if (lastLetter != null) {
                if (!lastLetter.equals(" ")&&last != 9&&!lastLetter.equals(")")&&!lastLetter.equals("(")&&!lastLetter.equals("|")&&!lastLetter.equals("+")) {                    
                    String text = tp.getText();
                    String[] lastWord = text.split("[" + Pattern.quote(" \n=)(") + "]");
                    String word = lastWord[lastWord.length-1];                    
                    String wordTrim = word.trim();
                    int start = text.lastIndexOf(wordTrim);
                    SimpleAttributeSet others = new SimpleAttributeSet(); 
                    if(wordTrim.equals("for")){
                        try {                                           
                           tp.getStyledDocument().remove(start,word.length());              
                        } catch (BadLocationException ex) {
                           Logger.getLogger(Create.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                             tp.getStyledDocument().insertString(start,"for(int i = 0;i<= 10;i++){ }", others);                                                                                                 
                        } catch (BadLocationException ex) {
                             Logger.getLogger(Create.class.getName()).log(Level.SEVERE, null, ex);
                        }
                          evt.consume();
                    }else if(wordTrim.equals("if")){
                        try {                                           
                           tp.getStyledDocument().remove(start,word.length());              
                        } catch (BadLocationException ex) {
                           Logger.getLogger(Create.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                             tp.getStyledDocument().insertString(start,"if(true){ }", others);                                                                                                 
                        } catch (BadLocationException ex) {
                             Logger.getLogger(Create.class.getName()).log(Level.SEVERE, null, ex);
                        }
                          evt.consume();
                    }
                    else  if (wordTrim.length() < 10 && wordTrim.length() > 0 && !wordTrim.equals("}")&& !wordTrim.equals("{")&& !wordTrim.equals("(")&& !wordTrim.equals(")")) {
                        SimpleAttributeSet attributes = new SimpleAttributeSet(); 
                        StyleConstants.setForeground(attributes,Color.RED);
                        SimpleAttributeSet normal = new SimpleAttributeSet(); 
                                  
                        StyleConstants.setForeground(normal,colorSelect);
                        try {                                           
                            tp.getStyledDocument().remove(start,word.length());              
                        } catch (BadLocationException ex) {
                            Logger.getLogger(Create.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {                            
                             tp.getStyledDocument().insertString(start,"<"+wordTrim+">", attributes);                                                                    
                             tp.getStyledDocument().insertString(tp.getStyledDocument().getLength()," ", normal); 
                             tp.getStyledDocument().insertString(tp.getStyledDocument().getLength(),"</"+wordTrim+">", attributes);                                                                    
                        } catch (BadLocationException ex) {
                             Logger.getLogger(Create.class.getName()).log(Level.SEVERE, null, ex);
                        }
                          evt.consume();
                    }                                             
                }                
            }                                   
            last =  evt.getKeyCode();
        }else{
            last =  0;
            
        }   
    }
    int count = 0;
    public void keyReleased(java.awt.event.KeyEvent evt){
        JTextPane pane = getEditor();
        String key = Character.toString(evt.getKeyChar()) ;
        String text = pane.getText();
        SimpleAttributeSet attributes = new SimpleAttributeSet(); 
        if (key.equals(" ")||key.equals("{")||key.equals("(")) {                                            
            String[] lastWord = text.split("[" + Pattern.quote(" }{)(\n") + "]");
            String word = lastWord[lastWord.length-1];
            int start = text.lastIndexOf(word);

            if (word.equalsIgnoreCase("java")||word.equalsIgnoreCase("public")||word.equalsIgnoreCase("private")||word.equalsIgnoreCase("protected")||word.equalsIgnoreCase("void")||word.equalsIgnoreCase("int")||word.equalsIgnoreCase("String")||word.equalsIgnoreCase("boolean")||word.equalsIgnoreCase("float")||word.equalsIgnoreCase("double")||word.equalsIgnoreCase("long")||word.equalsIgnoreCase("short")||word.equalsIgnoreCase("if")||word.equalsIgnoreCase("else")||word.equalsIgnoreCase("while")||word.equalsIgnoreCase("for")||word.equalsIgnoreCase("return")||word.equalsIgnoreCase("false")||word.equalsIgnoreCase("true")||word.equalsIgnoreCase("new")||word.equalsIgnoreCase("try")||word.equalsIgnoreCase("catch")||word.equalsIgnoreCase("this")||word.equalsIgnoreCase("extends")) {
                
                StyleConstants.setForeground(attributes,Color.BLUE);
                SimpleAttributeSet normal = new SimpleAttributeSet(); 
                StyleConstants.setForeground(normal,colorSelect);
                try {                                           
                    pane.getStyledDocument().remove(start,word.length());              
                } catch (BadLocationException ex) {
                    Logger.getLogger(Create.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                     pane.getStyledDocument().insertString(start,word , attributes);                                         
                     if (!key.equals(" ")) {
                         pane.getStyledDocument().insertString(pane.getStyledDocument().getLength(),key , normal); 
                     }else{
                         pane.getStyledDocument().insertString(pane.getStyledDocument().getLength(),key , normal); 
                     }
                } catch (BadLocationException ex) {
                     Logger.getLogger(Create.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }  
        else if(key.equals("\"")){                
                count++;
               
                String[] last = text.split("[" + Pattern.quote("\"") + "]");
                String word = last[last.length-1];
                int inicio = text.lastIndexOf(word);
                if (count == 2) {
                    count = 0;
                    StyleConstants.setForeground(attributes,Color.GREEN);
                     SimpleAttributeSet normal = new SimpleAttributeSet(); 
                     StyleConstants.setForeground(normal,colorSelect);
                    if (!Character.toString(text.charAt(inicio+1)).equals(key)) {
                        try {                                           
                            pane.getStyledDocument().remove(inicio,word.length());              
                        } catch (BadLocationException ex) {
                            
                        }
                        try {                                         
                            pane.getStyledDocument().insertString(inicio, word, attributes);
                            pane.getStyledDocument().insertString(pane.getStyledDocument().getLength(), " ", normal);
                        } catch (BadLocationException ex) {
                            Logger.getLogger(Create.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }                   
                }
            }
    }
    private void tpEditorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tpEditorKeyReleased
         keyReleased(evt);                  
    }//GEN-LAST:event_tpEditorKeyReleased

    private void mtNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mtNovoActionPerformed
        iniciarTab(1);
    }//GEN-LAST:event_mtNovoActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        new Sobre().setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        iniciarTab(1);
    }//GEN-LAST:event_jButton2ActionPerformed
    private static JPanel getTitlePanel(final JTabbedPane tabbedPane, final JPanel panel, String title){
         JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
         titlePanel.setOpaque(false);
         JLabel titleLbl = new JLabel(title);
         titleLbl.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
         titlePanel.add(titleLbl);
         JButton closeButton = new JButton("x");
            closeButton.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
                    tabbedPane.remove(panel);
                }
            });
         titlePanel.add(closeButton);

         return titlePanel;
    }
     public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Create().setVisible(true);
            }
        });        
    }
    /**
     * @param args the command line arguments
     */
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn;
    private javax.swing.JToggleButton btnItalico;
    private javax.swing.JToggleButton btnNegrito;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JToggleButton btnSublinhado;
    private javax.swing.JComboBox cbFont;
    private javax.swing.JComboBox cbFontSize;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator10;
    private javax.swing.JToolBar.Separator jSeparator11;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JToolBar.Separator jSeparator6;
    private javax.swing.JToolBar.Separator jSeparator7;
    private javax.swing.JToolBar.Separator jSeparator8;
    private javax.swing.JToolBar.Separator jSeparator9;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JButton lblDesfazer;
    private javax.swing.JButton lblRefazer;
    private javax.swing.JMenuItem mtNovo;
    private javax.swing.JPanel plAbas;
    private javax.swing.JTabbedPane tpAbas2;
    private javax.swing.JTextPane tpEditor;
    // End of variables declaration//GEN-END:variables
     
}
 
