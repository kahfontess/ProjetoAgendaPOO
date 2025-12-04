
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;


public class Main
{

    static int maxRegistros=1000;
    static String nomeAgenda="AgendaTeste";
    static Agenda ag=new Agenda(maxRegistros,nomeAgenda);
    static int nCadastros=-1;

    public static void main(String[] args)  throws IOException
    {
        String nm;
        String cel;
        String eml;
        LocalDate niver;
        int dia,mes,ano;
        int status;
        int registro;


        int st;

        boolean flagFim=false;
        LocalDate dataEspecifica = LocalDate.of(2025, 8, 29);

        BufferedReader teclado = new BufferedReader (new InputStreamReader (System.in));

        while (flagFim==false)
        {
            System.out.println("\n\nSISTEMA DE AGENDA:  "+nomeAgenda+"\n");
            System.out.println("1 - CADASTAR");
            System.out.println("2 - CONSULTAR");
            System.out.println("3 - ALTERAR");
            System.out.println("4 - LISTAR");
            System.out.println("5 - DELETAR");
            System.out.println("6 - LISTAR ANIVERSARIO");
            System.out.println("7 - LER DADOS ARQUIVO");
            System.out.println("8 - GRAVAR DADOS ARQUIVO");
            System.out.println("\n0 - SAIR");

            System.out.print("DIGITE A OPCAO:  ");
            String vl = teclado.readLine ();

            switch(vl)
            {
                case "0":
                    flagFim=true;
                    break;

                case "1":
                    System.out.print("\nCADASTRAR CONTATO:  ");
                    System.out.print("\nDIGITE NOME        ");
                    nm = teclado.readLine ();
                    System.out.print("\nDIGITE CELULAR:    ");
                    cel = teclado.readLine ();
                    System.out.print("\nDIGITE EMAIL:      ");
                    eml = teclado.readLine ();

                    System.out.print("\nDIA NASCIMENTO:  ");
                    vl = teclado.readLine ();
                    dia= Integer.parseInt(vl);
                    System.out.print("\nMES NASCIMENTO:  ");
                    vl = teclado.readLine ();
                    mes= Integer.parseInt(vl);
                    System.out.print("\nANO NASCIMENTO:  ");
                    vl = teclado.readLine ();
                    ano= Integer.parseInt(vl);
                    dataEspecifica = LocalDate.of(ano, mes, dia);

                    status=ag.Cadastrar(nm,cel,eml,dataEspecifica);
                    if ((status==1)||(status==2))
                    {
                        nCadastros=ag.getNumeroCadastros();
                    }

                    System.out.print("\nSTATUS DA OPERACAO: "+ag.getMensagemStatus(status));

                    break;

                case "2":
                    System.out.print("\nCONSULTAR POR NOME:  ");
                    System.out.print("\nDIGITE NOME        ");
                    nm = teclado.readLine ();
                    registro=ag.Consultar(nm);
                    if (registro>=0)
                    {
                        System.out.println("\nREGISTRO ["+String.format("%d",registro)+"]");
                        System.out.println("NOME:       "+ag.getNome(registro));
                        System.out.println("CELULAR:    "+ag.getCelular(registro));
                        System.out.println("EMAIL:      "+ag.getEmail(registro));
                        dataEspecifica = ag.getDataAniversario(registro);
                        System.out.println("NASCIMENTO: "+dataEspecifica);
                    }
                    else
                        System.out.print("\nERRO: NOME NAO CADASTRADO");
                    break;

                case "3":
                    System.out.print("\nALTERAR DADOS POR NOME:  ");
                    System.out.print("\nDIGITE NOME        ");
                    nm = teclado.readLine ();
                    registro=ag.Consultar(nm);
                    if (registro>=0)
                    {
                        nm=ag.getNome(registro);
                        cel=ag.getCelular(registro);
                        eml=ag.getEmail(registro);
                        System.out.println("\nREGISTRO ["+String.format("%d",registro)+"]");
                        System.out.println("NOME:       "+nm);
                        System.out.println("CELULAR:    "+cel);
                        System.out.println("EMAIL:      "+eml);
                        dataEspecifica = ag.getDataAniversario(registro);
                        dia = dataEspecifica.getDayOfMonth();
                        mes = dataEspecifica.getMonthValue();
                        ano = dataEspecifica.getYear();
                        System.out.println("NASCIMENTO: "+dataEspecifica);

                        System.out.print("\nDESEJA REALMENTE ALTERAR OS DADOS <S/N>: ");
                        String op = teclado.readLine ();
                        if (op.equals("S")||op.equals("s"))
                        {
                            System.out.print("\nALTERAR DADOS DO CONTATO:  ");
                            System.out.print("\nDIGITE NOVO NOME      ");
                            vl = teclado.readLine ();
                            if(!vl.equals(""))
                                nm=vl;
                            System.out.print("\nDIGITE NOVO CELULAR:  ");
                            vl = teclado.readLine ();
                            if(!vl.equals(""))
                                cel=vl;
                            System.out.print("\nDIGITE NOVO EMAIL:    ");
                            vl = teclado.readLine ();
                            if(!vl.equals(""))
                                eml=vl;

                            System.out.print("\nNOVO DIA NASCIMENTO:  ");
                            vl = teclado.readLine ();
                            if(!vl.equals(""))
                                dia= Integer.parseInt(vl);
                            System.out.print("\nNONO MES NASCIMENTO:  ");
                            vl = teclado.readLine ();
                            if(!vl.equals(""))
                                mes= Integer.parseInt(vl);
                            System.out.print("\nNONO ANO NASCIMENTO:  ");
                            vl = teclado.readLine ();
                            if(!vl.equals(""))
                                ano= Integer.parseInt(vl);
                            dataEspecifica = LocalDate.of(ano, mes, dia);

                            status=ag.Alterar(registro,nm,cel,eml,dataEspecifica);
                            System.out.print("\nSTATUS DA OPERACAO: "+ag.getMensagemStatus(status));

                        }
                    }
                    else
                        System.out.print("\nERRO: NOME NAO CADASTRADO");
                    break;

                case "4":
                    System.out.print("\nLISTAR DADOS CADASTRADOS:\n");
                    for(int i=0; i<nCadastros ; i++)
                    {
                        st=ag.getStatus(i);
                        if((st==1)||(st==2))
                        {
                            System.out.println("\nREGISTRO ["+String.format("%d",i)+"]");
                            System.out.println("NOME:       "+ag.getNome(i));
                            System.out.println("CELULAR:    "+ag.getCelular(i));
                            System.out.println("EMAIL:      "+ag.getEmail(i));
                            dataEspecifica = ag.getDataAniversario(i);
                            System.out.println("NASCIMENTO: "+dataEspecifica);
                        }
                    }

                    System.out.println("\nTOTAL DE CONTATOS ATIVOS: " + ag.getNumeroCadastros());
                    break;

                case "5":
                    System.out.print("\nDELETAR DADOS POR NOME:  ");
                    System.out.print("\nDIGITE NOME        ");
                    nm = teclado.readLine ();
                    registro=ag.Consultar(nm);
                    if (registro>=0)
                    {
                        nm=ag.getNome(registro);
                        cel=ag.getCelular(registro);
                        eml=ag.getEmail(registro);
                        System.out.println("\nREGISTRO ["+String.format("%d",registro)+"]");
                        System.out.println("NOME:       "+nm);
                        System.out.println("CELULAR:    "+cel);
                        System.out.println("EMAIL:      "+eml);
                        dataEspecifica = ag.getDataAniversario(registro);
                        dia = dataEspecifica.getDayOfMonth();
                        mes = dataEspecifica.getMonthValue();
                        ano = dataEspecifica.getYear();
                        System.out.println("NASCIMENTO: "+dataEspecifica);

                        System.out.print("\nDESEJA REALMENTE DELETAR OS DADOS <S/N>: ");
                        String op = teclado.readLine ();
                        if (op.equals("S")||op.equals("s"))
                        {
                            st=ag.Deletar(registro);
                            if (st==3)
                            {
                                System.out.print("\nDADOS DELETADOS COM SUCESSO");
                            }
                            else
                            {
                                System.out.print("\nDADOS NAO DELETADOS");
                            }
                        }
                    }
                    break;

                case "6":
                    System.out.print("\nLISTAR CADASTRO POR DATA DE ANIVERSARIO:\n");
                    System.out.print("\nDIA NASCIMENTO:  ");
                    vl = teclado.readLine ();
                    dia= Integer.parseInt(vl);
                    System.out.print("\nMES NASCIMENTO:  ");
                    vl = teclado.readLine ();
                    mes= Integer.parseInt(vl);
                    System.out.print("\nANO NASCIMENTO:  ");
                    vl = teclado.readLine ();
                    ano= Integer.parseInt(vl);

                    dataEspecifica = LocalDate.of(ano, mes, dia);

                    int totalAniversariantes = 0;

                    for(int i=0; i<nCadastros ; i++)
                    {
                        st=ag.getStatus(i);
                        if((st==1)||(st==2))
                        {
                            niver=ag.getDataAniversario(i);
                            if(niver.compareTo(dataEspecifica)==0)
                            {
                                System.out.println("\nREGISTRO ["+String.format("%d",i)+"]");
                                System.out.println("NOME:       "+ag.getNome(i));
                                System.out.println("CELULAR:    "+ag.getCelular(i));
                                System.out.println("EMAIL:      "+ag.getEmail(i));
                                System.out.println("NASCIMENTO: "+niver);
                                totalAniversariantes++;
                            }
                        }
                    }

                    System.out.println("\nTOTAL DE ANIVERSARIANTES NESSA DATA: " + totalAniversariantes);
                    if (totalAniversariantes == 0) {
                        System.out.println("Nenhum contato faz aniversario nessa data.");
                    }
                    break;

                case "7":
                    ag.Inicializar();

                    System.out.print("\nLER DADOS DO ARQUIVO:  ");
                    nCadastros=ag.lerArquivo();
                    if (nCadastros>=0)
                    {
                        System.out.print("\nLEITURA DOS DADOS EFETUADA COM SUCESSO");
                        System.out.print("\nNUMERO DE REGISTROS: "+String.format("%d",nCadastros));
                    }
                    else
                        System.out.print("\nERRO: LEITURA DOS DADOS NAO EFETUADA COM SUCESSO");
                    break;

                case "8":
                    nCadastros=ag.gravarArquivo();
                    if (nCadastros>=0)
                    {
                        System.out.print("\nGRAVACAO DOS DADOS EFETUADA COM SUCESSO");
                        System.out.print("\nNUMERO DE REGISTROS: "+String.format("%d",nCadastros));
                    }
                    else
                        System.out.print("\nERRO: GRAVACAO DOS DADOS NAO EFETUADA COM SUCESSO");

                    break;
                default:
            }

        }

    }
}
