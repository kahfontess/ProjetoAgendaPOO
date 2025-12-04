import java.util.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;


public class Dados
{

    private String nome,celular,email;
    private String mensagemAniversario;
    private LocalDate dataAniversario;

    private int status;

    public Dados()
    {
        nome="";
        celular="";
        email="";
        dataAniversario=LocalDate.now();
        mensagemAniversario = "";
        status=0;
    };

    public void Inicializar()
    {
        nome="";
        celular="";
        email="";
        dataAniversario=LocalDate.now();
        mensagemAniversario = "";
        status=0;
    };


    public int Cadastrar(String nm, String cel, String eml, LocalDate niver)
    {
        if (status==0)
        {
            nome=nm;
            celular=cel;
            email=eml;
            dataAniversario=niver;
            status=1;
        }

        return status;
    };


    public int Alterar(String nm, String cel, String eml, LocalDate niver)
    {
        if ((status==1)||(status==2))
        {
            nome=nm;
            celular=cel;
            email=eml;
            dataAniversario=niver;
            status=2;
        }

        return status;
    };

    public String getNome()
    {
        if ((status==1)||(status==2))
        {
            return nome;
        }

        return null;
    };

    public String getCelular()
    {
        if ((status==1)||(status==2))
        {
            return celular;
        }

        return null;
    };

    public String getEmail()
    {
        if ((status==1)||(status==2))
        {
            return email;
        }

        return null;
    };

    public LocalDate getDataAniversario()
    {
        if ((status==1)||(status==2))
        {
            return dataAniversario;
        }

        return null;
    };

    public int getStatus()
    {
        return status;
    };

    public String getMensagemAniversario()
    {
        if ((status==1)||(status==2))
        {
            return mensagemAniversario;
        }

        return null;
    };

    public boolean Consultar(String nm)
    {
        if ((nome.equals(nm))&&((status==1)||(status==2)))
        {
            return true;
        }
        return false;
    };

    public boolean Deletar(String nm)
    {
        if ((nome.equals(nm))&&((status==1)||(status==2)))
        {
            status=3;
            return true;
        }
        return false;
    }

    public boolean Deletar()
    {
        if ((status==1)||(status==2))
        {
            status=3;
            return true;
        }
        return false;
    }

    public String validarAniversario( LocalDate d)
    {
        if(dataAniversario.compareTo(d)==0)
            mensagemAniversario="PARABENS, FELIZ ANIVERSARIO";
        else
            mensagemAniversario="AINDA NAO Eh O DIA DO SEU ANIVERSARIO";

        return mensagemAniversario;
    };

}
