/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Presentacion;

import Datos.CategoriaDAO;
import Datos.MarcaDAO;
import Entidades.Categoria;
import Entidades.Marca;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class FrmCategoriasMarcas extends javax.swing.JInternalFrame {

    private CategoriaDAO categoriaDAO = new CategoriaDAO();
    private MarcaDAO marcaDAO = new MarcaDAO();
    private DefaultTableModel modeloCategorias;
    private DefaultTableModel modeloMarcas;
    private int idCategoriaSeleccionada = -1;
    private int idMarcaSeleccionada = -1;

    public FrmCategoriasMarcas() {
        initComponents();
        
        // Habilitar opciones del menú de la ventana
        setClosable(true);
        setMaximizable(true);
        setIconifiable(true);
        setResizable(true);
        setTitle("Gestión de Categorías y Marcas");
        
        configurarTablas();
        cargarCategorias();
        cargarMarcas();
    }
    
    private void configurarTablas() {
        // Tabla de categorías
        modeloCategorias = new DefaultTableModel(
            new Object[]{"ID", "Nombre", "Descripción", "Estado"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblCategorias.setModel(modeloCategorias);
        
        // Tabla de marcas
        modeloMarcas = new DefaultTableModel(
            new Object[]{"ID", "Nombre", "Estado"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblMarcas.setModel(modeloMarcas);
    }
    
    private void cargarCategorias() {
        modeloCategorias.setRowCount(0);
        List<Categoria> lista = categoriaDAO.listarTodas();
        for (Categoria c : lista) {
            modeloCategorias.addRow(new Object[]{
                c.getIdCategoria(),
                c.getNombre(),
                c.getDescripcion(),
                c.getEstado() == 1 ? "Activo" : "Inactivo"
            });
        }
    }
    
    private void cargarMarcas() {
        modeloMarcas.setRowCount(0);
        List<Marca> lista = marcaDAO.listarTodas();
        for (Marca m : lista) {
            modeloMarcas.addRow(new Object[]{
                m.getIdMarca(),
                m.getNombre(),
                m.getEstado() == 1 ? "Activo" : "Inactivo"
            });
        }
    }
    
    private void limpiarCamposCategoria() {
        txtNombreCategoria.setText("");
        txtDescripcionCategoria.setText("");
        idCategoriaSeleccionada = -1;
        tblCategorias.clearSelection();
    }
    
    private void limpiarCamposMarca() {
        txtNombreMarca.setText("");
        idMarcaSeleccionada = -1;
        tblMarcas.clearSelection();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        panelCategorias = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNombreCategoria = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtDescripcionCategoria = new javax.swing.JTextField();
        btnGuardarCategoria = new javax.swing.JButton();
        btnEliminarCategoria = new javax.swing.JButton();
        btnLimpiarCategoria = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCategorias = new javax.swing.JTable();
        panelMarcas = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtNombreMarca = new javax.swing.JTextField();
        btnGuardarMarca = new javax.swing.JButton();
        btnEliminarMarca = new javax.swing.JButton();
        btnLimpiarMarca = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblMarcas = new javax.swing.JTable();

        // Panel Categorías
        jLabel1.setText("Nombre:");
        jLabel2.setText("Descripción:");

        btnGuardarCategoria.setText("Guardar");
        btnGuardarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarCategoriaActionPerformed(evt);
            }
        });

        btnEliminarCategoria.setText("Desactivar");
        btnEliminarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarCategoriaActionPerformed(evt);
            }
        });

        btnLimpiarCategoria.setText("Limpiar");
        btnLimpiarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarCategoriaActionPerformed(evt);
            }
        });

        tblCategorias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {"ID", "Nombre", "Descripción", "Estado"}
        ));
        tblCategorias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCategoriasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCategorias);

        javax.swing.GroupLayout panelCategoriasLayout = new javax.swing.GroupLayout(panelCategorias);
        panelCategorias.setLayout(panelCategoriasLayout);
        panelCategoriasLayout.setHorizontalGroup(
            panelCategoriasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCategoriasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCategoriasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)
                    .addGroup(panelCategoriasLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombreCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDescripcionCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelCategoriasLayout.createSequentialGroup()
                        .addComponent(btnGuardarCategoria)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminarCategoria)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLimpiarCategoria)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelCategoriasLayout.setVerticalGroup(
            panelCategoriasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCategoriasLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelCategoriasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNombreCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtDescripcionCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelCategoriasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarCategoria)
                    .addComponent(btnEliminarCategoria)
                    .addComponent(btnLimpiarCategoria))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Categorías", panelCategorias);

        // Panel Marcas
        jLabel3.setText("Nombre:");

        btnGuardarMarca.setText("Guardar");
        btnGuardarMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarMarcaActionPerformed(evt);
            }
        });

        btnEliminarMarca.setText("Desactivar");
        btnEliminarMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarMarcaActionPerformed(evt);
            }
        });

        btnLimpiarMarca.setText("Limpiar");
        btnLimpiarMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarMarcaActionPerformed(evt);
            }
        });

        tblMarcas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {"ID", "Nombre", "Estado"}
        ));
        tblMarcas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMarcasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblMarcas);

        javax.swing.GroupLayout panelMarcasLayout = new javax.swing.GroupLayout(panelMarcas);
        panelMarcas.setLayout(panelMarcasLayout);
        panelMarcasLayout.setHorizontalGroup(
            panelMarcasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMarcasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMarcasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)
                    .addGroup(panelMarcasLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombreMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelMarcasLayout.createSequentialGroup()
                        .addComponent(btnGuardarMarca)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminarMarca)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLimpiarMarca)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelMarcasLayout.setVerticalGroup(
            panelMarcasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMarcasLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelMarcasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNombreMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelMarcasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarMarca)
                    .addComponent(btnEliminarMarca)
                    .addComponent(btnLimpiarMarca))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Marcas", panelMarcas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarCategoriaActionPerformed
        String nombre = txtNombreCategoria.getText().trim();
        String descripcion = txtDescripcionCategoria.getText().trim();
        
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el nombre de la categoría", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Categoria c = new Categoria();
        c.setNombre(nombre);
        c.setDescripcion(descripcion);
        c.setEstado(1); // Activo por defecto
        
        boolean exito;
        if (idCategoriaSeleccionada == -1) {
            // Insertar nueva
            exito = categoriaDAO.insertar(c);
            if (exito) {
                JOptionPane.showMessageDialog(this, "Categoría registrada correctamente");
            }
        } else {
            // Actualizar existente
            c.setIdCategoria(idCategoriaSeleccionada);
            exito = categoriaDAO.actualizar(c);
            if (exito) {
                JOptionPane.showMessageDialog(this, "Categoría actualizada correctamente");
            }
        }
        
        if (exito) {
            cargarCategorias();
            limpiarCamposCategoria();
        } else {
            JOptionPane.showMessageDialog(this, "Error al guardar la categoría", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarCategoriaActionPerformed

    private void btnEliminarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarCategoriaActionPerformed
        int fila = tblCategorias.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una categoría de la tabla", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int id = (int) tblCategorias.getValueAt(fila, 0);
        String nombre = (String) tblCategorias.getValueAt(fila, 1);
        
        int confirmacion = JOptionPane.showConfirmDialog(this, 
            "¿Está seguro de desactivar la categoría \"" + nombre + "\"?", 
            "Confirmar desactivación", 
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.QUESTION_MESSAGE);
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            if (categoriaDAO.desactivar(id)) {
                JOptionPane.showMessageDialog(this, "Categoría desactivada correctamente");
                cargarCategorias();
                limpiarCamposCategoria();
            } else {
                JOptionPane.showMessageDialog(this, "Error al desactivar la categoría", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnEliminarCategoriaActionPerformed

    private void btnLimpiarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarCategoriaActionPerformed
        limpiarCamposCategoria();
    }//GEN-LAST:event_btnLimpiarCategoriaActionPerformed

    private void tblCategoriasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCategoriasMouseClicked
        int fila = tblCategorias.getSelectedRow();
        if (fila != -1) {
            idCategoriaSeleccionada = (int) tblCategorias.getValueAt(fila, 0);
            txtNombreCategoria.setText((String) tblCategorias.getValueAt(fila, 1));
            txtDescripcionCategoria.setText((String) tblCategorias.getValueAt(fila, 2));
        }
    }//GEN-LAST:event_tblCategoriasMouseClicked

    private void btnGuardarMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarMarcaActionPerformed
        String nombre = txtNombreMarca.getText().trim();
        
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el nombre de la marca", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Marca m = new Marca();
        m.setNombre(nombre);
        m.setEstado(1); // Activo por defecto
        
        boolean exito;
        if (idMarcaSeleccionada == -1) {
            // Insertar nueva
            exito = marcaDAO.insertar(m);
            if (exito) {
                JOptionPane.showMessageDialog(this, "Marca registrada correctamente");
            }
        } else {
            // Actualizar existente
            m.setIdMarca(idMarcaSeleccionada);
            exito = marcaDAO.actualizar(m);
            if (exito) {
                JOptionPane.showMessageDialog(this, "Marca actualizada correctamente");
            }
        }
        
        if (exito) {
            cargarMarcas();
            limpiarCamposMarca();
        } else {
            JOptionPane.showMessageDialog(this, "Error al guardar la marca", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarMarcaActionPerformed

    private void btnEliminarMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarMarcaActionPerformed
        int fila = tblMarcas.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una marca de la tabla", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int id = (int) tblMarcas.getValueAt(fila, 0);
        String nombre = (String) tblMarcas.getValueAt(fila, 1);
        
        int confirmacion = JOptionPane.showConfirmDialog(this, 
            "¿Está seguro de desactivar la marca \"" + nombre + "\"?", 
            "Confirmar desactivación", 
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.QUESTION_MESSAGE);
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            if (marcaDAO.desactivar(id)) {
                JOptionPane.showMessageDialog(this, "Marca desactivada correctamente");
                cargarMarcas();
                limpiarCamposMarca();
            } else {
                JOptionPane.showMessageDialog(this, "Error al desactivar la marca", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnEliminarMarcaActionPerformed

    private void btnLimpiarMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarMarcaActionPerformed
        limpiarCamposMarca();
    }//GEN-LAST:event_btnLimpiarMarcaActionPerformed

    private void tblMarcasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMarcasMouseClicked
        int fila = tblMarcas.getSelectedRow();
        if (fila != -1) {
            idMarcaSeleccionada = (int) tblMarcas.getValueAt(fila, 0);
            txtNombreMarca.setText((String) tblMarcas.getValueAt(fila, 1));
        }
    }//GEN-LAST:event_tblMarcasMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminarCategoria;
    private javax.swing.JButton btnEliminarMarca;
    private javax.swing.JButton btnGuardarCategoria;
    private javax.swing.JButton btnGuardarMarca;
    private javax.swing.JButton btnLimpiarCategoria;
    private javax.swing.JButton btnLimpiarMarca;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel panelCategorias;
    private javax.swing.JPanel panelMarcas;
    private javax.swing.JTable tblCategorias;
    private javax.swing.JTable tblMarcas;
    private javax.swing.JTextField txtDescripcionCategoria;
    private javax.swing.JTextField txtNombreCategoria;
    private javax.swing.JTextField txtNombreMarca;
    // End of variables declaration//GEN-END:variables
}

