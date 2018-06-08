/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ba.poo2.beans;

import br.ba.poo2.mapeamento.Passagem;
import br.ba.poo2.modelo.PassagemModelo;
import br.ba.poo2.rn.PassagemRN;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "passagemBean")
@SessionScoped
public class PassagemBean {
  PassagemRN passagemRN;
    
    
    private Passagem passagemSelecionada;
    private List<Passagem> passagens;
    private Passagem passagem;
    private PassagemModelo modelo;
    
 
    
    public void popularDataTable(){
        passagemRN = new PassagemRN();
        this.passagens = passagemRN.listarSemFiltro();
        modelo = new PassagemModelo(passagens);
    }
    
    public PassagemBean() {
        passagem = new Passagem();
        popularDataTable();
    }

    public Passagem getPassagemSelecionada() {
        return passagemSelecionada;
    }

    public void setPassagemSelecionada(Passagem passagemSelecionada) {
        this.passagemSelecionada = passagemSelecionada;
    }

    public List<Passagem> getPassagens() {
        return passagens;
    }

    public void setPassagem(List<Passagem> passagens) {
        this.passagens = passagens;
    }

    public Passagem getPassagem(){
        return passagem;
    }

    public void setPassagem(Passagem passagem) {
        this.passagem = passagem;
    }
    
    public PassagemModelo getModelo() {
        return modelo;
    }

    public void setModelo(PassagemModelo modelo) {
        this.modelo = modelo;
    }
    
    public void abrirDialog(){
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('wdgdlgAl').show()");
        //requestContext.execute("PF('wdgdlgAl').show()"); no prime 5
    }
    public void abrirDialogAlt(){
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('wdgdlgAlalt').show()");
    }
    
    
    public void salvar(){
        try {
            passagemRN = new PassagemRN();
            passagemRN.salvar(passagem);
            this.passagens = passagemRN.listarSemFiltro();
            passagem = new Passagem();
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Inclusão", "Passagem incluída com Sucesso!");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        } catch (Exception e) {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha", "Ocorreu um erro na inclusão!");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
    }
    public void alterar(){
        passagemRN = new PassagemRN();
        passagemRN.alterar(passagemSelecionada);
        this.passagens = passagemRN.listarSemFiltro();
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alteração", "Passagem alterada com Sucesso!");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }
    public void excluir(){
        passagemRN = new PassagemRN();
        passagemRN.excluir(passagemSelecionada);
        this.passagens = passagemRN.listarSemFiltro();
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exclusão", "Passagem excluída com Sucesso!");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }        
}
