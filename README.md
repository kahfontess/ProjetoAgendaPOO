Relatório do Projeto Agenda em Java 

Aluno(a): Carlos Eduardo Fontes Camacho   Turma: 218063P   Data: 04/09/2025 

 

1. Implementação do Sistema.
   
O projeto implementa uma aplicação de linha de comando em Java para gerenciamento de uma agenda de contatos. A agenda permite cadastrar, consultar, alterar, listar e deletar contatos,
além de listar aniversários por data específica e realizar leitura e gravação dos dados em arquivo texto no formato de 6 linhas por registro. A classe Dados.java representa um contato
individual, com os campos nome, celular, e-mail, data de aniversário, mensagem de aniversário e status. Nessa classe foram implementados os métodos de cadastro, alteração, consulta,
deleção, validação de aniversário e os métodos getters. A classe Agenda.java é responsável por gerenciar um vetor de objetos Dados, com capacidade para até 1000 contatos. Nela foram
implementados os métodos para cadastrar, consultar, alterar e deletar contatos (por nome e por posição), além de métodos para retornar os campos por posição e os métodos de persistência
lerArquivo() e gravarArquivo(), que trabalham com o arquivo texto no formato de 6 linhas por contato. A classe Main.java implementa o menu interativo em modo texto, exibindo as 8 opções
solicitadas: cadastrar, consultar, alterar, listar, deletar, listar aniversários, ler dados de arquivo TXT e gravar dados em arquivo TXT. Essa classe realiza a leitura dos dados digitados
pelo usuário, converte dia/mês/ano para LocalDate, chama os métodos da classe Agenda e apresenta mensagens de status para cada operação. 

2. Testes Realizados.
   
Foram realizados testes manuais seguindo o roteiro sugerido no enunciado. Inicialmente, foi utilizada a opção de leitura para importar o arquivo AgendaTeste.txt e conferir a quantidade de
registros carregados com sucesso. Em seguida, foram feitas consultas a nomes existentes e a um nome inexistente, verificando as mensagens de retorno. Na sequência, foi testada a alteração
de dados de um contato (e-mail e celular), acompanhada de uma nova listagem para conferir se as mudanças foram aplicadas corretamente. Também foi testada a funcionalidade de deleção,
removendo um contato e confirmando que ele deixou de aparecer na listagem de registros ativos. A funcionalidade de listagem de aniversários foi exercitada com uma data presente no conjunto
de dados e com uma data ausente, verificando as mensagens exibidas. Por fim, foi utilizada a opção de gravação em arquivo texto, seguida de limpeza da agenda em memória e nova leitura do
arquivo gerado, para validar a simetria entre leitura e gravação dos dados. 

3. Observações sobre Persistência e Tratamento de Erros.
   
Na parte de persistência, foi respeitado o contrato de 6 linhas por registro, tanto na leitura quanto na gravação do arquivo texto. Na exportação, são gravados apenas os contatos com
status ativo, garantindo que registros deletados não sejam reaproveitados indevidamente. Também foram tratados os principais erros de entrada e saída de arquivo, retornando códigos negativos
em caso de falhas na leitura ou gravação. O desenvolvimento deste projeto permitiu praticar conceitos de vetores de objetos, controle de status, manipulação de datas com LocalDate e operações
de entrada e saída em arquivos texto. A interface em modo texto foi organizada para apresentar mensagens claras sobre o resultado de cada operação para o usuário.

