/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ba.poo2.beans;

import br.ba.poo2.mapeamento.Cidade;
import br.ba.poo2.modelo.CidadeModelo;
//import br.ba.poo2.modelo.CidadeModelo;
import br.ba.poo2.rn.CidadeRN;
import java.io.Serializable;
import java.util.List;
import java.util.HashMap;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;


@ManagedBean(name = "cidadeBean")
@SessionScoped
public class CidadeBean implements Serializable{
   /*private String nomeCidade;
    private String idCidade;
    private String uf;
    private int id_TabelaCidade;

    public String getNomeCidade() {
        return nomeCidade;
    }

    public void setNomeCidade(String nomeCidade) {
        this.nomeCidade = nomeCidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }*/
      private CidadeRN cidadeRN;
    
    
    private Cidade cidadeSelecionado;
    private List<Cidade> cidades;
    private Cidade cidade;
    private CidadeModelo modelo;
    
    
    private int tipoRelatorio;
    
    public void popularDataTable(){
        cidadeRN = new CidadeRN();
        this.cidades = cidadeRN.listarSemFiltro();
        modelo = new CidadeModelo(cidades);
    }
    
    public CidadeBean() {
        cidade = new Cidade();
        popularDataTable();
    }

    public Cidade getCidadeSelecionado() {
        return cidadeSelecionado;
    }

    public void setCidadeSelecionado(Cidade cidadeSelecionado) {
        this.cidadeSelecionado = cidadeSelecionado;
    }

    public List<Cidade> getCidades() {
        return cidades;
    }

    public void setCidades(List<Cidade> cidades) {
        this.cidades = cidades;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
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
            cidadeRN = new CidadeRN();
            cidadeRN.salvar(cidade);
            this.cidades = cidadeRN.listarSemFiltro();
            cidade = new Cidade();
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Inclusão", "Cidade incluída com Sucesso!");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        } catch (Exception e) {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha", "Ocorreu um erro na inclusão!");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
    }
    public void alterar(){
        cidadeRN = new CidadeRN();
        cidadeRN.alterar(cidadeSelecionado);
        this.cidades = cidadeRN.listarSemFiltro();
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alteração", "Cidade alterada com Sucesso!");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }
    public void excluir(){
        cidadeRN = new CidadeRN();
        cidadeRN.excluir(cidadeSelecionado);
        this.cidades = cidadeRN.listarSemFiltro();
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exclusão", "Cidade excluída com Sucesso!");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }
}
