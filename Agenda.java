import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import java.util.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

public class Agenda
{

    private int maxCadastros=1000;
    static private Dados[] dd;   //=new Dados[1000];
    private int nCadastros;

    private String nomeAgenda;


    public Agenda(int tam, String nmAgenda)
    {
        nomeAgenda=nmAgenda;
        maxCadastros=tam;
        dd=new Dados[maxCadastros];
        nCadastros=0;

        for(int i=0;i<maxCadastros;i++)
        {
            dd[i]=new Dados();
        }
    };

    public Agenda()
    {
        nomeAgenda="Agenda";
        dd=new Dados[maxCadastros];
        nCadastros=0;
        for(int i=0;i<maxCadastros;i++)
        {
            dd[i]=new Dados();
        }
    };


    public void Inicializar()
    {
        nCadastros=0;
        for(int i=0;i<maxCadastros;i++)
        {
            dd[i].Inicializar();
        }
    }


    public int getNumeroCadastros()
    {
        return nCadastros;
    }


    public int Cadastrar(String nm, String cel, String eml, LocalDate niver)
    {
        int status;

        status=dd[nCadastros].Cadastrar(nm, cel, eml, niver);
        if (status==1)
        {
            nCadastros++;
        }

        return status;
    };

    public String getMensagemStatus(int st)
    {
        switch(st)
        {
            case 0:
                return "NAO CADASTRADO";
            case 1:
                return "CADASTRO OK";
            case 2:
                return "CADASTRO ALTERADO";
            case 3:
                return "CADASTRO DELETADO";
            default:
                return "STATUS NAO VALIDO";
        }
    };


    public int Alterar(int pos,String nm, String cel, String eml, LocalDate niver)
    {
        int status=dd[pos].getStatus();
        if((status==1)||(status==2))
        {
            status=dd[pos].Alterar(nm, cel, eml, niver);
        }

        return status;
    };


    public int Deletar(String nm)
    {
        int pos=Consultar(nm);
        int status=0;
        if (pos>=0)
        {
            status=dd[pos].getStatus();

            if((status==1)||(status==2))
            {
                if(dd[pos].Deletar(nm)==true)
                    return 3;
            }
        }
        return status;
    };


    public int Deletar(int p)
    {
        if (dd[p].Deletar()==true)
            return 3;

        return -1;
    };

    public int Consultar(String nm)
    {
        int registro=-1;

        for (int i = 0 ; i<nCadastros;i++)
        {
            if(dd[i].Consultar(nm)==true)
            {
                System.out.println("REGISTRO RETORNO ["+String.format("%d",i)+"]: ");
                return i;
            }
        }
        return registro;
    };

    public String getNome(int pos)
    {
        int st=dd[pos].getStatus();
        if ((st==1)||(st==2))
        {
            return dd[pos].getNome();
        }

        return null;
    };

    public String getCelular(int pos)
    {
        int st=dd[pos].getStatus();
        if ((st==1)||(st==2))
        {
            return dd[pos].getCelular();
        }

        return null;
    };

    public String getEmail(int pos)
    {
        int st=dd[pos].getStatus();
        if ((st==1)||(st==2))
        {
            return dd[pos].getEmail();
        }

        return null;
    };

    public LocalDate getDataAniversario(int pos)
    {
        int st=dd[pos].getStatus();
        if ((st==1)||(st==2))
        {
            return dd[pos].getDataAniversario();
        }

        return null;
    };

    public int getStatus(int pos)
    {
        if ((pos >= 0) && (pos < nCadastros))
        {
            return dd[pos].getStatus();
        }

        return 0;
    };

    public int lerArquivo()
    {
        String nm;
        String cel;
        String eml;
        LocalDate niver;
        int dia,mes,ano;
        int status;

        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(nomeAgenda+".txt"));
            String linha=" ";
            while (linha != null)
            {

                linha = reader.readLine();
                if (linha!=null)
                {
                    nm=linha;


                    linha=reader.readLine();
                    cel=linha;

                    linha=reader.readLine();
                    eml=linha;


                    linha=reader.readLine();
                    dia=Integer.parseInt(linha);

                    linha=reader.readLine();
                    mes=Integer.parseInt(linha);

                    linha=reader.readLine();
                    ano=Integer.parseInt(linha);

                    niver=LocalDate.of(ano, mes, dia);

                    status=Cadastrar(nm, cel, eml, niver);
                }
            }

            reader.close();
        }
        catch (FileNotFoundException e)
        {
            return -1;
        }
        catch (IOException e)
        {
            return -2;
        }

        return nCadastros;
    };


    public int gravarArquivo()
    {
        String nm;
        String cel;
        String eml;
        LocalDate niver;
        int dia,mes,ano;

        try
        {
            BufferedWriter bufferWrite = new BufferedWriter(new FileWriter(nomeAgenda+".txt"));
            for (int i = 0;i<nCadastros;i++)
            {
                int st = dd[i].getStatus();
                if (st == 1 || st == 2) {
                    nm = dd[i].getNome();
                    cel = dd[i].getCelular();
                    eml = dd[i].getEmail();
                    niver = dd[i].getDataAniversario();

                    if (nm != null && cel != null && eml != null && niver != null) {
                        bufferWrite.append(nm + "\n");
                        bufferWrite.append(cel + "\n");
                        bufferWrite.append(eml + "\n");

                        dia = niver.getDayOfMonth();
                        mes = niver.getMonthValue();
                        ano = niver.getYear();
                        bufferWrite.append(dia+"\n");
                        bufferWrite.append(mes+"\n");
                        bufferWrite.append(ano+"\n");
                    }
                }
            }
            bufferWrite.close();
        }
        catch (FileNotFoundException e)
        {
            return -1;
        }
        catch (IOException e)
        {
            return -2;
        }

        return nCadastros;
    };


};
