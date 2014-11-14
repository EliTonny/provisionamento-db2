/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PDF;

import Sistema.Dao;
import Sistema.Factoring;
import Sistema.Session;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import provisionamento.model.GrupoComunitario;
import provisionamento.model.GrupoUnitario;
import provisionamento.model.Participante;

/**
 *
 * @author Lucas
 */
public class MinhasDividasReport {
    
    public MinhasDividasReport(String caminho) throws Exception {
        Document doc = null;
        OutputStream os = null;
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        boolean isVazio = true;

        try {
            //cria o documento tamanho A4, margens de 2,54cm
            doc = new Document(PageSize.A4, 72, 72, 72, 72);

            //cria a stream de saída
            os = new FileOutputStream(caminho);
            PdfWriter.getInstance(doc, os);
            doc.open();
            
            Paragraph cabecalho = new Paragraph("Sistema de Provisionamento");
            cabecalho.setAlignment(Element.ALIGN_CENTER);
            doc.add(cabecalho);

            Font fBig = new Font(FontFamily.COURIER, 20, Font.NORMAL);
            Paragraph p1 = new Paragraph("Relatório das Dívidas de " + 
                                          Session.getInstancia().getUsuarioLogado().getNome(), fBig);
            p1.setAlignment(Element.ALIGN_CENTER);
            doc.add(p1);
            
            Paragraph p2 = new Paragraph(" "); 
            doc.add(p2);

            PdfPTable tableUnit = new PdfPTable(2);
            Paragraph pHeaderUnit = new Paragraph("Grupos Unitários");
            PdfPCell headerUnit = new PdfPCell(pHeaderUnit);
            headerUnit.setColspan(3);
            tableUnit.addCell(headerUnit);
            
            Dao<GrupoUnitario> daoGrupoUnit = Factoring.getDaoGrupoUnitario();
            for (GrupoUnitario g : daoGrupoUnit.busca()) {
                 if(g.getCriador().equals(Session.getInstancia().getUsuarioLogado()) &&
                   (!(g.isFinalizado()))){
                     isVazio = false;
                     tableUnit.addCell(g.toString());
                     tableUnit.addCell("R$" + Double.toString(g.getValorCompra()));
                 }
             }
            
            if (!(isVazio)){
                doc.add(tableUnit);
            }
            
            
            isVazio = true;
            Paragraph p3 = new Paragraph(" ");
            doc.add(p3);

            PdfPTable tableComun = new PdfPTable(2);
            Paragraph pHeaderComun = new Paragraph("Grupos Comunitários");
            PdfPCell headerComun = new PdfPCell(pHeaderComun);
            headerComun.setColspan(3);
            tableComun.addCell(headerComun);
            
            Dao<GrupoComunitario> daoGrupoComun = Factoring.getDaoGrupoComunitario();
            for (GrupoComunitario g : daoGrupoComun.busca()) {
                if(!(g.isFinalizado()) &&
                  (!(g.isPago()))) {
                    for(Participante p :g.getParticipantes()){
                        if(p.getUsuario().equals(Session.getInstancia().getUsuarioLogado())){
                            if(!(p.isPago())){
                                isVazio = false;
                                tableComun.addCell(g.toString());
                                tableComun.addCell("R$" + Double.toString(g.getValorCompra()));
                            }
                        }
                    }
                    
                }
             }
            
            if(!(isVazio)){
                doc.add(tableComun);
            }
            
            doc.add(p3);
            Font fRodape = new Font(FontFamily.COURIER, 8, Font.NORMAL);
            Paragraph rodape = new Paragraph("Relatório gerado em " + sdf1.format(new Date()), fRodape);
            rodape.setAlignment(Element.ALIGN_RIGHT);
            doc.add(rodape);
            
            } finally {
                if (doc != null) {
                    //fechamento do documento
                    doc.close();
                }
                if (os != null) {
                   //fechamento da stream de saída
                   os.close();
                }
                
                JOptionPane.showMessageDialog(null, "Relatório Gerado com Sucesso!");
            }
    }    
}
