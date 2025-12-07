/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Presentacion;

import Datos.VentaDAO;
import Entidades.Venta;
import com.toedter.calendar.JDateChooser;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author macbook
 */
public class FrmReporteVentas extends javax.swing.JInternalFrame {

    private VentaDAO ventaDAO = new VentaDAO();
    private DefaultTableModel modelo;

    public FrmReporteVentas() {
        initComponents();
        
        // Habilitar opciones del menú de la ventana
        setClosable(true);
        setMaximizable(true);
        setIconifiable(true);
        setResizable(true);
        setTitle("Reporte de Ventas");
        
        // Configurar fecha de hoy en ambos campos
        Date hoy = new Date();
        dateInicio.setDate(hoy);
        dateFin.setDate(hoy);
        
        // Configurar tabla
        configurarTabla();
        
        // Cargar todas las ventas al iniciar
        cargarVentas();
    }
    
    private void configurarTabla() {
        modelo = new DefaultTableModel(
            new Object[]{"ID Venta", "Fecha", "Cliente", "Vendedor", "Subtotal", "IGV", "Total"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblVentas.setModel(modelo);
    }
    
    private void cargarVentas() {
        modelo.setRowCount(0);
        List<Venta> lista = ventaDAO.listar();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        
        for (Venta v : lista) {
            modelo.addRow(new Object[]{
                v.getIdVenta(),
                sdf.format(v.getFecha()),
                ventaDAO.obtenerNombreCliente(v.getIdCliente()),
                ventaDAO.obtenerNombreUsuario(v.getIdUsuario()),
                String.format("S/ %.2f", v.getSubtotal()),
                String.format("S/ %.2f", v.getIgv()),
                String.format("S/ %.2f", v.getTotal())
            });
        }
        
        lblTotal.setText("Total de ventas: " + lista.size());
    }
    
    private void filtrarPorFecha() {
        Date fechaInicio = dateInicio.getDate();
        Date fechaFin = dateFin.getDate();
        
        if (fechaInicio == null || fechaFin == null) {
            JOptionPane.showMessageDialog(this, "Seleccione ambas fechas", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (fechaInicio.after(fechaFin)) {
            JOptionPane.showMessageDialog(this, "La fecha de inicio no puede ser mayor a la fecha fin", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        modelo.setRowCount(0);
        List<Venta> lista = ventaDAO.listarPorFecha(fechaInicio, fechaFin);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        
        double totalGeneral = 0;
        for (Venta v : lista) {
            modelo.addRow(new Object[]{
                v.getIdVenta(),
                sdf.format(v.getFecha()),
                ventaDAO.obtenerNombreCliente(v.getIdCliente()),
                ventaDAO.obtenerNombreUsuario(v.getIdUsuario()),
                String.format("S/ %.2f", v.getSubtotal()),
                String.format("S/ %.2f", v.getIgv()),
                String.format("S/ %.2f", v.getTotal())
            });
            totalGeneral += v.getTotal();
        }
        
        lblTotal.setText("Ventas encontradas: " + lista.size() + " | Total: S/ " + String.format("%.2f", totalGeneral));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblVentas = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        dateInicio = new com.toedter.calendar.JDateChooser();
        dateFin = new com.toedter.calendar.JDateChooser();
        btnFiltrar = new javax.swing.JButton();
        btnMostrarTodo = new javax.swing.JButton();
        lblTotal = new javax.swing.JLabel();

        tblVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {"ID Venta", "Fecha", "Cliente", "Vendedor", "Subtotal", "IGV", "Total"}
        ));
        jScrollPane1.setViewportView(tblVentas);

        jLabel10.setText("Fecha Inicio:");

        jLabel11.setText("Fecha Fin:");

        btnFiltrar.setText("Filtrar");
        btnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarActionPerformed(evt);
            }
        });

        btnMostrarTodo.setText("Mostrar Todo");
        btnMostrarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarTodoActionPerformed(evt);
            }
        });

        lblTotal.setFont(new java.awt.Font("Helvetica Neue", 1, 13));
        lblTotal.setText("Total de ventas: 0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateFin, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnFiltrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnMostrarTodo)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTotal)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(jLabel11))
                    .addComponent(dateInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnFiltrar)
                        .addComponent(btnMostrarTodo)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTotal)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
        filtrarPorFecha();
    }//GEN-LAST:event_btnFiltrarActionPerformed

    private void btnMostrarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarTodoActionPerformed
        dateInicio.setDate(new Date());
        dateFin.setDate(new Date());
        cargarVentas();
    }//GEN-LAST:event_btnMostrarTodoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JButton btnMostrarTodo;
    private com.toedter.calendar.JDateChooser dateFin;
    private com.toedter.calendar.JDateChooser dateInicio;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTable tblVentas;
    // End of variables declaration//GEN-END:variables
}
