# PROJETO RETIRADA DE MATERIAIS

Projeto para gerenciar os empréstimos e devoluções de materiais da sala de técnicos do IFSP - Campus Salto

## Descrição do problema

Em uma sala do campus, são guardados todas as ferramentas e componentes eletrônicos que os alunos do instituto podem **emprestar** para realizar seus projetos. Porém, é necessário realizar um **controle** desses empréstimos, para que uma ferramenta ou componente seja perdido. Pensando nisso, foi desenvolvido um
sistema para **realizar o controle dos empréstimos realizados**.

## Funções do projeto

Para que o projeto faça o controle dos empréstimos de uma forma efetiva, é necessário definir as funções,
que ele deve cumprir. Dessa forma, foram definidas as seguintes:

- Armazenar dados dos componentes disponíveis na sala
- Fazer empréstimo de componente disponível na sala
- Fazer devolução de componente disponível na sala
- Armazenar emprestimos em andamento
- Histórico de devoluções e empréstimos

## Idealização

Tendo em mente todas essas funções necessárias, foi idelizado um sistema que consiste em uma **interface** em **Java** executada em um **Raspberry Pi**, conectado a um **display touch HDMI Nextion** e dentro de um **case** feito em MDF. Através do display, o usuário pode interagir com a interface, e realizar empréstimos, devoluções, adicionar componentes, etc. Todos os dados inseridos pelo usuário na interface são armazenados em um **banco de dados SQLite3**, fazendo assim com que os dados não sejam perdidos por uma eventual reinicialização do sistema.

![PROJETO RETIRADA DE MATERIAIS](case/Perspectiva.jpg)

## Componentes

Tendo essa ideia em mente, foram adquiridos os seguintes componentes:

### Raspberry Pi

Microprocessador do projeto, responsável por executar a interface java e armazenar todos os dados necessários.

![Raspberry](planejamento/Componentes/raspberry%20pi.png)

### Display touch HDMI Nextion

Tela do projeto, responsável por mostrar a interface e permitir a interação do usuário.

![Display](planejamento/Componentes/Nextion.jpg)

## Case

Para criação do case do projeto, foi desenvolvido um projeto no site [maker case](https://pt.makercase.com/) para a criação de um case com abas, para uma melhor fixação. Além, foi utilizado também o Autodesk Fusion 360, para fazer os cortes que seriam necessários para o encaixe do Raspberry Pi.

![case](case/Case%20design.PNG)

Após isso, cada lado da caixa foi exportado em um arquivo DXF para ser cortado em uma máquina de corte a laser.

Esquema de corte:

![Corte](case/Case%20parts%20design.PNG)

## Banco de dados

O [SQLite3](https://www.sqlite.org/index.html), é um banco de dados baseado em SQL que armazena os dados em um arquivo *.db*, e nos permite gerenciá-lo diretamente da linguagem de programação, por esse motivo, torna-se ideal para projetos de sistemas embarcados.

Para esse projeto, foi criado o arquivo **RetiradaDeMateriais.db** que contém as seguintes tabelas com as seguintes colunas:

Loan:

- id - Identificação do empréstimo
- name - Nome do usuário
- componentID - Identificação do componente emprestado
- quantity - Quantidade do componente emprestado
- loanDate - Data da realização do empréstimo componente
- devolutionDate - Data em que o componente foi devolvido
- status - Empréstimo em andamento ou ja devolvido

Components:

- id - Identificação do componente
- component - Nome do componente
- qtdAvailable - Quantidade disponível desse componente
- qtdUnavailable - Quantidade emprestada desse componente

## Interface

A interface do projeto foi desenvolvida em **Java**, utilizando o framework **JavaFX** junto com o **SceneBuilder** para a criação das janelas. Além disso, para uma melhor organização do projeto, foram criadas pastas baseadas na arquitetura **MVC** (Model-View-Controller) que são responsáveis pelas seguintes funções:

- Model - Contém as classes principais para a lógica do projeto, e tem como função gerenciar a conexão com o Banco de dados SQLite3.
- View - Contém os arquivos de layout *.fxml* para cada janela e tem como função capturar o input do usuário.
- Controller - Contém as classes de controle de cada layout e tem como função receber as informações do *view* e "enviá-las" para o *Model*.

Exemplo de um modelo MVC:

![Modelo MVC](./Planejamento/Imagens%20documenta%C3%A7%C3%A3o/Modelo%20MVC.png)

### Layouts

Foram desenvolvidos 5 janelas que apresentam os seguintes layouts:

#### Tela Inicial - HomeScreen

A Tela Inicial da interface é responsável por **exibir** os empréstimos **Em andamento**, e permitir com que seja feita uma devolução.

![Tela Inicial](./planejamento/Interface%20Designs/HomeScreen.png)

- Botão - Empréstimo - Abre a janela Novo empréstimo
- Botão - Componentes - Abre a janela Componentes
- Botão - Histórico - Abre a janela Histórico
- Tabela - Empréstimos em andamento - Mostra os empréstimos com status **Em andamento**
- Caixa de texto - Pesquisar - Permite que o usuário pesquise um empréstimo na tabela
- Botão - Search - pesquisa um empréstimo na tabela
- Botão - Devolver - Atualiza o status do empréstimo para **Devolvido**

#### Novo Empréstimo - NewLoan

A tela Novo Empréstimo é responsável por **inserir** um **novo empréstimo** no banco de dados a partir da seleção de componentes na tabela *componentes* disponíveis por parte do usuário.

![Novo Emprestimo](./planejamento/Interface%20Designs/NewLoan.png)

- Botão - Empréstimo - Abre a janela Novo empréstimo
- Botão - Componentes - Abre a janela Componentes
- Botão - Histórico - Abre a janela Histórico
- Caixa de texto - Nome - Espaço para o usuário colocar seu nome
- Tabela - Componentes a serem emprestados - Mostra os componentes selecionados para empréstimo pelo usuário
- Tabela - Componentes disponíveis - Mostra os componentes e sua quantidade disponível para que o usuário possa selecioná-lo para empréstimo.
- Caixa de texto - Pesquisar - Permite que o usuário pesquise um componente na tabela *componentes disponíveis*
- Botão - Search - pesquisa um componente na tabela *componentes disponíveis*
- Caixa de texto - Quantidade - Permite que o usuário digite a quantidade a ser emprestada daquele componente
- Botão - Adicionar - Adiciona o componente selecionado da tabela *componentes disponíveis* e sua respectiva quantidade, para a tabela *componentes a serem emprestados*
- Botão - Realizar empréstimo - cria um novo empréstimo inserindo todos os dados fornecidos no banco de dados

#### Componentes - Components

A tela Componentes é responsável por mostrar **todos** os **componentes** que estão no banco de dados.

![Componentes](./planejamento/Interface%20Designs/Components.png)

- Botão - Empréstimo - Abre a janela Novo empréstimo
- Botão - Componentes - Abre a janela Componentes
- Botão - Histórico - Abre a janela Histórico
- Tabela - Componentes - Mostra os componentes inseridos no banco
- Caixa de texto - Pesquisar - Permite que o usuário pesquise um componente na tabela
- Botão - Search - pesquisa um componente na tabela
- Botão - Novo componente - Abre a janela Novo Componente

#### Histórico - History

A tela Histórico é responsável por mostrar **todos** os **empréstimos** que já foram realizados, tanto os que estão **Em andamento**, como os **devolvidos**.

![Historico](./planejamento/Interface%20Designs/History.png)

- Botão - Empréstimo - Abre a janela Novo empréstimo
- Botão - Componentes - Abre a janela Componentes
- Botão - Histórico - Abre a janela Histórico
- Tabela - Histórico - Mostra todos os empréstimos armazenados no banco de dados.
- Caixa de texto - Pesquisar - Permite que o usuário pesquise um componente na tabela
- Botão - Search - pesquisa um empréstimo na tabela

#### Novo Componente - NewComponent

A tela Novo Componente é responsável por **inserir** um **componente** e sua respectiva **quantidade** no banco de dados.

![Novo Componente](./planejamento/Interface%20Designs/NewComponent.png)

- Botão - Empréstimo - Abre a janela Novo empréstimo
- Botão - Componentes - Abre a janela Componentes
- Botão - Histórico - Abre a janela Histórico
- Caixa de texto - Nome - Permite que o usuário digite o nome do componente a ser inserido
- Caixa de texto - Quantidade - Permite que o usuário digite a quantidade do componente a ser inserido
- Botão - Adicionar Componente - adiciona um novo componente, inserindo todos os dados fornecidos no banco.

### Lógica

Para a criação da lógica do projeto, foram criadas as seguintes classes:

- Model:
  - App.java - Classe principal do projeto, tem como função iniciar o sistema.

  - Component.java - Classe auxiliar para criar um objeto *componente* a partir dos dados armazenados no banco.

  - Loan.java - Classe auxiliar para criar um objeto *emprestimo* a partir dos dados.

  - SQLConnection.java - Classe de conexão com o banco de dados.

- Controller:
  - HomeScreenController.java - Classe para controle da *Tela Inicial*.

  - ComponentsController.java - Classe para controle da janela *Componentes*.

  - HistoryController.java - Classe para controle da janela *Histórico*.

  - NewLoanController.java - Classe para controle da janela *Novo Empréstimo*.

  - NewComponentController.java - Classe para controle da janela *Novo Componente*.

#### App.java

  A classe App.java é a *classe principal* da interface, e tem como função iniciar o programa, através do método
  **start()**, que é herdado da classe Application do JavaFX. Além disso, a classe também contém o método **changeScene()** que tem como função fazer as trocas de layout, e recebe como parâmetro o *path* do layout a ser mostrado, e a janela a ser mudada.

```
public class App extends Application{
    Stage primaryStage; // Janela da aplicação

    public static void main(String[] args) throws Exception {
        launch(args); // Inicia o programa
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        changeScene(getClass().getResource("/view/HomeScreen.fxml"), primaryStage); //Mostra o layout HomeScreen

    }

    public static void changeScene(URL path, Stage stage){
        try {
            Parent root = FXMLLoader.load(path);          // Carrega o arquivo FXML do layout
            Scene scene = new Scene(root, 720, 1280);     // Cria uma nova scene com o layout
            stage.setScene(scene);                        // Muda o layout 
            stage.show();                                 // Mostra o layout
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
```

#### Component.java

  A classe Component.java tem como função criar o objeto *Componente*.

```
public class Component {
    private int id;                   // Atributos de um componente
    private String component;
    private int qtdAvailable;
    private int qtdUnavailable;

    public Component(int id, String component, int qtdAvailable, int qtdUnavailable) {   // Método Construtor
        this.id = id;
        this.component = component;
        this.qtdAvailable = qtdAvailable;
        this.qtdUnavailable = qtdUnavailable;
    }

    public int getId() {              // Métodos getters e setters
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getComponent() {
        return component;
    }
    public void setComponent(String component) {
        this.component = component;
    }
    public int getQtdAvailable() {
        return qtdAvailable;
    }
    public void setQtdAvailable(int qtdAvailable) {
        this.qtdAvailable = qtdAvailable;
    }
    public int getQtdUnavailable() {
        return qtdUnavailable;
    }
    public void setQtdUnavailable(int qtdUnavailable) {
        this.qtdUnavailable = qtdUnavailable;
    }
}
```

#### Loan.java

  Assim como a classe *Component.java*, a classe Loan.java tem como função criar o objeto *Empréstimo*.

```
public class Loan {
    private int id;                         // Atributos de um empréstimo
    private String name;
    private String component;
    private int quantity;
    private String loanDate;
    private String devolutionDate = null;
    private String status;
    private SQLConnection sqlConnection;
    private SimpleDateFormat dateFormat;
    private ResultSet result;

    // Método construtor

    public Loan(int id, String name, int componentId, int quantity, Date loanDate, Date devolutionDate,
            boolean status) throws SQLException {  
            
        this.sqlConnection = new SQLConnection();                 // Criando conexão com o banco de dados
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");          // Convertendo data para sistema brasileiro

        // Buscando no Banco de dados o componente correspondente ao ComponentID passado como parâmetro

        result = sqlConnection.getComponent("Id", componentId);   

        while(result.next()){
            this.component = result.getString("component");
            
        }

        this.id = id;                                             
        this.name = name;
        this.quantity = quantity;
        this.loanDate = dateFormat.format(loanDate);

        // Checando se existe ou não data de devolução

        if(devolutionDate != null){                                       
            this.devolutionDate = dateFormat.format(devolutionDate);
        }else{
            this.devolutionDate = "-";
        }

        // Verificando se o emprestimo está em andamento

        if(status){
            this.status = "Em andamento";
        }else{
            this.status = "Devolvido";
        }
        
    }
    
    public int getid() {                  // Métodos getters e setters
        return id;
    }
    public void setid(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getComponent() {
        return this.component;
    }
    public void setcomponent(String component) {
        this.component = component;
    }
    public int getQuantity() {
        return this.quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public String getLoanDate() {
        return this.loanDate;
    }
    public void setLoanDate(String loanDate) {
        this.loanDate = loanDate;
    }
    public String getDevolutionDate() {
        return this.devolutionDate;
    }
    public void setDevolutionDate(String devolutionDate) {
        this.devolutionDate = devolutionDate;
    }
    public String isStatus() {
        return this.status;
    }
    public void setStatus(String status) {
        this.status = status;
    } 

}
```

#### SQLConnection.java

  A classe SQLConnection.java é responsável por criar e conectar com o banco de dados RetiradaDeMateriais.db, juntamente com suas tabelas *Componente* e *Empréstimo*, além fazer todas as manipulações necessárias através dos métodos *GET (SELECT)*, *INSERT*, *UPDATE*, *DELETE*, que foram criados tanto para a tabela *Componente* como para *Empréstimo* utilizando o polimorfismo para criar os métodos para cada tipo de parâmetro necessários. Além disso, a classe também contém um método para desconectar do banco de dados.

  ```
  public class SQLConnection {
    private Connection connection;                     // Atributos de uma conexão SQLITE3
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    
    public SQLConnection(){                            // Método construtor
        try {

            // Conectando com o banco

            this.connection = DriverManager.getConnection("jdbc:sqlite:" + "./RetiradaDeMateriais.db");
            
            this.statement = this.connection.createStatement();     // Criando Statement para manipulação  

            // Criando tabelas

            this.statement.execute("CREATE TABLE IF NOT EXISTS Components (Id INTEGER PRIMARY KEY AUTOINCREMENT, component TEXT, qtdAvailable INTEGER, qtdUnavailable INTEGER);");
            this.statement.execute("CREATE TABLE IF NOT EXISTS Loan (Id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, componentId INTEGER, quantity INTEGER, loanDate DATE, devolutionDate DATE, status BOOLEAN);");

        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco");
            e.printStackTrace();
        }
    }

    public boolean close(){                         // Método para desconexão
        try {
            if(!this.statement.isClosed()){
                this.statement.close();
            
                if(!this.connection.isClosed()){
                    this.connection.close();
                }

                return true;
            }else{
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao fechar banco");
            return false;
        }
    }

    // Métodos GET (SELECT), INSERT, UPDATE E DELETE para cada tabela

    public void insertLoan(String name, int componentId, int qtdComponent, Date loanDate, boolean status){
        try {
            this.preparedStatement = this.connection.prepareStatement("INSERT INTO Loan (name, componentId, quantity, loanDate, devolutionDate, status) VALUES (?,?,?,?,?,?);");
            this.preparedStatement.setString(1, name);
            this.preparedStatement.setInt(2, componentId);
            this.preparedStatement.setInt(3, qtdComponent);
            this.preparedStatement.setDate(4, loanDate);
            this.preparedStatement.setDate(5, null);
            this.preparedStatement.setBoolean(6, status);

            this.preparedStatement.executeUpdate();
                
            
        } catch (SQLException e) {
            System.out.println("Falha ao inserir dados na tabela");
            e.printStackTrace();
        }
    }

    public ResultSet getLoan(){
        try {
            resultSet = statement.executeQuery("SELECT * FROM Loan"); 
            return resultSet;
        } catch (SQLException e) {
            System.out.println("Erro ao selecionar dados da tabela loan");
            e.printStackTrace();
            return null;
        }
    }

    public ResultSet getLoan(String key, String value){
        try {
            this.preparedStatement = connection.prepareStatement("SELECT * FROM Loan WHERE " + key + " = ?");
            this.preparedStatement.setString(1, value);
            this.resultSet = this.preparedStatement.executeQuery();
            return this.resultSet;
        } catch (SQLException e) {
            System.out.println("Falha ao selecionar dados a partir de chave da tabela loan");
            e.printStackTrace();
            return null;
        }
    }

    public ResultSet getLoan(String key, int value){
        try {
            this.preparedStatement = connection.prepareStatement("SELECT * FROM Loan WHERE " + key + " = ?");
            this.preparedStatement.setInt(1, value);
            this.resultSet = this.preparedStatement.executeQuery();
            return this.resultSet;
        } catch (SQLException e) {
            System.out.println("Falha ao selecionar dados a partir de chave da tabela loan");
            e.printStackTrace();
            return null;
        }
    }

    public ResultSet getLoan(String key, boolean value){
        try {
            this.preparedStatement = connection.prepareStatement("SELECT * FROM Loan WHERE " + key + " = ?");
            this.preparedStatement.setBoolean(1, value);
            this.resultSet = this.preparedStatement.executeQuery();
            return this.resultSet;
        } catch (SQLException e) {
            System.out.println("Falha ao selecionar dados a partir de chave da tabela loan");
            e.printStackTrace();
            return null;
        }
    }


    public void updateLoan(String key, String keyValue, String field, String fieldValue){
        try {
            this.preparedStatement = connection.prepareStatement("UPDATE Loan SET " + field + " = ? WHERE " + key + " = ?");
            this.preparedStatement.setString(1, fieldValue);
            this.preparedStatement.setString(2, keyValue);
            this.preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Falha ao atualizar dados a partir de chave da tabela loan");
            e.printStackTrace();
        }
    }

    public void updateLoan(String key, int keyValue, String field, String fieldValue){
        try {
            this.preparedStatement = connection.prepareStatement("UPDATE Loan SET " + field + " = ? WHERE " + key + " = ?");
            this.preparedStatement.setString(1, fieldValue);
            this.preparedStatement.setInt(2, keyValue);
            this.preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Falha ao atualizar dados a partir de chave da tabela loan");
            e.printStackTrace();
        }
    }

    public void updateLoan(String key, String keyValue, String field, int fieldValue){
        try {
            this.preparedStatement = connection.prepareStatement("UPDATE Loan SET " + field + " = ? WHERE " + key + " = ?");
            this.preparedStatement.setInt(1, fieldValue);
            this.preparedStatement.setString(2, keyValue);
            this.preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Falha ao atualizar dados a partir de chave da tabela loan");
            e.printStackTrace();
        }
    }

    public void updateLoan(String key, int keyValue, String field, int fieldValue){
        try {
            this.preparedStatement = connection.prepareStatement("UPDATE Loan SET " + field + " = ? WHERE " + key + " = ?");
            this.preparedStatement.setInt(1, fieldValue);
            this.preparedStatement.setInt(2, keyValue);
            this.preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Falha ao atualizar dados a partir de chave da tabela loan");
            e.printStackTrace();
        }
    }

    public void updateLoan(String key, int keyValue, String field, boolean fieldValue){
        try {
            this.preparedStatement = connection.prepareStatement("UPDATE Loan SET " + field + " = ? WHERE " + key + " = ?");
            this.preparedStatement.setBoolean(1, fieldValue);
            this.preparedStatement.setInt(2, keyValue);
            this.preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Falha ao atualizar dados a partir de chave da tabela loan");
            e.printStackTrace();
        }
    }

    public void updateLoan(String key, int keyValue, String field, Date fieldValue){
        try {
            this.preparedStatement = connection.prepareStatement("UPDATE Loan SET " + field + " = ? WHERE " + key + " = ?");
            this.preparedStatement.setDate(1, fieldValue);
            this.preparedStatement.setInt(2, keyValue);
            this.preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Falha ao atualizar dados a partir de chave da tabela loan");
            e.printStackTrace();
        }
    }

    public void deleteLoan(String key, String value){
        try {
            this.preparedStatement = connection.prepareStatement("DELETE FROM Loan WHERE " + key + " = ?");
            this.preparedStatement.setString(1, value);
            this.preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Falha ao deletar dados a partir de chave da tabela loan");
            e.printStackTrace();
        }
    }

    public void deleteLoan(String key, int value){
        try {
            this.preparedStatement = connection.prepareStatement("DELETE FROM Loan WHERE " + key + " = ?");
            this.preparedStatement.setInt(1, value);
            this.preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Falha ao deletar dados a partir de chave da tabela loan");
            e.printStackTrace();
        }
    }


    public void insertComponent(String component, int qtdAvailable, int qtdUnavailable){
        try {
            this.preparedStatement = this.connection.prepareStatement("INSERT INTO Components (component, qtdAvailable, qtdUnavailable) VALUES (?,?,?);");
            this.preparedStatement.setString(1, component);
            this.preparedStatement.setInt(2, qtdAvailable);
            this.preparedStatement.setInt(3, qtdUnavailable);

            this.preparedStatement.executeUpdate();
                
            
        } catch (SQLException e) {
            System.out.println("Falha ao inserir dados na tabela");
            e.printStackTrace();
        }
    }    

    public ResultSet getComponent(){
        try {
            this.resultSet = statement.executeQuery("SELECT * FROM Components"); 
            return this.resultSet;
        } catch (SQLException e) {
            System.out.println("Erro ao selecionar dados da tabela Components");
            e.printStackTrace();
            return null;
        }
    }

    public ResultSet getComponent(String key, String value){
        try {
            this.preparedStatement = connection.prepareStatement("SELECT * FROM Components WHERE " + key + " = ?");
            this.preparedStatement.setString(1, value);
            this.resultSet = this.preparedStatement.executeQuery();
            return this.resultSet;
        } catch (SQLException e) {
            System.out.println("Falha ao selecionar dados a partir de chave da tabela Components");
            e.printStackTrace();
            return null;
        }
    }

    public ResultSet getComponent(String key, int value){
        try {
            this.preparedStatement = connection.prepareStatement("SELECT * FROM Components WHERE " + key + " = ?");
            this.preparedStatement.setInt(1, value);
            this.resultSet = this.preparedStatement.executeQuery();
            return this.resultSet;
        } catch (SQLException e) {
            System.out.println("Falha ao selecionar dados a partir de chave da tabela Components");
            e.printStackTrace();
            return null;
        }
    }

    public void updateComponent(String key, String keyValue, String field, String fieldValue){
        try {
            this.preparedStatement = connection.prepareStatement("UPDATE Components SET " + field + " = ? WHERE " + key + " = ?");
            this.preparedStatement.setString(1, fieldValue);
            this.preparedStatement.setString(2, keyValue);
            this.preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Falha ao atualizar dados a partir de chave da tabela Components");
            e.printStackTrace();
        }
    }

    public void updateComponent(String key, int keyValue, String field, String fieldValue){
        try {
            this.preparedStatement = connection.prepareStatement("UPDATE Components SET " + field + " = ? WHERE " + key + " = ?");
            this.preparedStatement.setString(1, fieldValue);
            this.preparedStatement.setInt(2, keyValue);
            this.preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Falha ao atualizar dados a partir de chave da tabela Components");
            e.printStackTrace();
        }
    }

    public void updateComponent(String key, String keyValue, String field, int fieldValue){
        try {
            this.preparedStatement = connection.prepareStatement("UPDATE Components SET " + field + " = ? WHERE " + key + " = ?");
            this.preparedStatement.setInt(1, fieldValue);
            this.preparedStatement.setString(2, keyValue);
            this.preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Falha ao atualizar dados a partir de chave da tabela Components");
            e.printStackTrace();
        }
    }

    public void updateComponent(String key, int keyValue, String field, int fieldValue){
        try {
            this.preparedStatement = connection.prepareStatement("UPDATE Components SET " + field + " = ? WHERE " + key + " = ?");
            this.preparedStatement.setInt(1, fieldValue);
            this.preparedStatement.setInt(2, keyValue);
            this.preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Falha ao atualizar dados a partir de chave da tabela Components");
            e.printStackTrace();
        }
    }

    public void deleteComponent(String key, String value){
        try {
            this.preparedStatement = connection.prepareStatement("DELETE FROM Components WHERE " + key + " = ?");
            this.preparedStatement.setString(1, value);
            this.preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Falha ao selecionar dados a partir de chave da tabela Components");
            e.printStackTrace();
        }
    }

    public void deleteComponent(String key, int value){
        try {
            this.preparedStatement = connection.prepareStatement("DELETE FROM Components WHERE " + key + " = ?");
            this.preparedStatement.setInt(1, value);
            this.preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Falha ao selecionar dados a partir de chave da tabela Components");
            e.printStackTrace();
        }
    }

}
  ```

#### HomeScreenController.java

A classe HomeScreenController.java é a classe de controle do layout *HomeScreen.fxml*, e tem como função controlar todos os componentes do layout, para fazer isso temos que criar um objeto para cada componente, utilizando as classes de *scene.control* do JavaFX e a tag *@FXML*. Para esse layout, precisamos controlar a tabela, suas colunas e a caixa de texto.

```
public class HomeScreenController implements Initializable{

    @FXML
    private AnchorPane pane;

    // Criando o objeto das colunas da tabela e atribuindo os tipos de objetos que serão exibidos

    @FXML
    private TableColumn<Loan, String> tblColumnComponent = new TableColumn<>();   

    @FXML
    private TableColumn<Loan, String> tblColumnDay = new TableColumn<>();

    @FXML
    private TableColumn<Loan, String> tblColumnName = new TableColumn<>();

    @FXML
    private TableColumn<Loan, Integer> tblColumnQtd = new TableColumn<>();

    @FXML
    private TableColumn<Loan, String> tblColumnStatus = new TableColumn<>();

    @FXML
    private TableView<Loan> tblLoans;          // Objeto da tabela

    @FXML
    private TextField txtSearch;              // Objeto da caixa de texto
```

É necessário também declararmos alguns objetos auxiliares para serem instânciados posteriormente.

```
private SQLConnection sqlConnection;                    // Objeto para conexão com o banco de dados
private ResultSet resultLoans, resultComponents;        // Objetos para selecionar os dados do banco
private Loan loan;                                      // Objeto da classe Emprestimo
private List<Loan> listLoans = new ArrayList<>();       // Lista de emprestimos 
private ObservableList<Loan> obsLoans;                  // Lista de emprestimos para exibirmos na coluna
private int selectedItem = -1;                          // Variável para armazenar o item selecionado na tabela
```

Para executarmos funções no momento em que o layout é iniciado, precisamos implementar o método *initialize()*, da interface java **Initializable**. Dessa forma, podemos preencher a tabela de empréstimos com os dados necessários automaticamente.

```
@Override
    public void initialize(URL location, ResourceBundle resources) {

        try{

            // Configura a coluna para mostrar os tipos de variáveis necessários

            tblColumnComponent.setCellValueFactory(new PropertyValueFactory<Loan, String>("component"));
            tblColumnName.setCellValueFactory(new PropertyValueFactory<Loan, String>("name"));
            tblColumnDay.setCellValueFactory(new PropertyValueFactory<Loan, String>("loanDate"));
            tblColumnQtd.setCellValueFactory(new PropertyValueFactory<Loan, Integer>("quantity"));
            tblColumnStatus.setCellValueFactory(new PropertyValueFactory<Loan, String>("status"));
            
            sqlConnection = new SQLConnection();                   // Conecta com o banco

            resultLoans = sqlConnection.getLoan("status", true);   // Seleciona os emprestimos com status true

            // Loop para adicionar cada emprestimo em uma lista de emprestimos

            while(resultLoans.next()){
                loan = new Loan(resultLoans.getInt("Id"),
                resultLoans.getString("name"),
                resultLoans.getInt("componentId"),
                resultLoans.getInt("quantity"),
                resultLoans.getDate("loanDate"),
                resultLoans.getDate("devolutionDate"),
                resultLoans.getBoolean("status"));

                listLoans.add(loan);
            }

            obsLoans = FXCollections.observableArrayList(listLoans);    // Transforma a lista em Observable
            tblLoans.setItems(obsLoans);                                // Atualiza a tabela

            sqlConnection.close();                                      // Desconecta do banco
        } catch (Exception e) {
            System.out.println("Falha ao selecionar dados");
            e.printStackTrace();
        }

    }
```

É necessário também, criar um método para cada botão. Para os botões **EMPRESTIMO**, **COMPONENTES** e **HISTORICO**, padrões para todas as janelas, utilizamos o método *changeScene* da classe *App.java*, para mudar o layout para **NewLoan.fxml**, **Components.fxml**, **History.fxml** respectivamente.

```
    @FXML
    void btnComponents(ActionEvent event) {       // Método para botão COMPONENTES
        App.changeScene(getClass().getResource("/view/Components.fxml"), (Stage) pane.getScene().getWindow());
    }

    @FXML
    void btnHistory(ActionEvent event) {          // Método para botão HISTORICO
        App.changeScene(getClass().getResource("/view/History.fxml"), (Stage) pane.getScene().getWindow());
    }

    @FXML
    void btnLoan(ActionEvent event) {             // Método para botão EMPRESTIMO
        App.changeScene(getClass().getResource("/view/NewLoan.fxml"), (Stage) pane.getScene().getWindow());
    }
```

Nesse layout, temos ainda os botões **DEVOLVER** e **SEARCH**, que irão executar algumas funções. O botão **DEVOLVER** será responsável por buscar no banco de dados o empréstimo que selecionado na tabela através de seu ID, adicionar a data da devolução e atualizar seu status para *FALSE*, além de também atualizar a quantidade de componentes disponíveis e emprestados. Também foi criado um método **tblClick()** para armazenar o ID do *emprestimo* que foi selecionado na tabela.

```
    @FXML
    void tblClick(MouseEvent event) {

        //Armazena na variável selectedItem o ID do emprestimo selecionado na tabela

        selectedItem = tblLoans.getSelectionModel().getSelectedIndex(); 
        System.out.println(selectedItem);
    }

    @FXML
    void btnDevolution(ActionEvent event) {
        if(selectedItem != -1){                             // Verifica se um emprestimo foi selecionado
            loan = tblLoans.getItems().get(selectedItem);   // Armazena o emprestimo que foi selecionado

            sqlConnection = new SQLConnection();            // Conecta com o banco de dados

            // Atualiza o Status e adicona data devolução,

            try{
                sqlConnection.updateLoan("Id", loan.getid(), "status", false);          
                sqlConnection.updateLoan("Id", loan.getid(), "devolutionDate", new Date(new java.util.Date().getTime()));

                // Atualiza quantidade de componentes

                resultComponents = sqlConnection.getComponent("component", loan.getComponent());
                System.out.println(loan.getQuantity());

                while(resultComponents.next()){
                    sqlConnection.updateComponent("Id", resultComponents.getInt("Id"), "qtdAvailable", resultComponents.getInt("qtdAvailable") + loan.getQuantity());
                    sqlConnection.updateComponent("Id", resultComponents.getInt("Id"), "qtdUnavailable", resultComponents.getInt("qtdUnavailable") - loan.getQuantity());

                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            sqlConnection.close();              // Fecha conexão com o banco de dados
            btnSearch(event);                   // Atualiza a tabela
        }
    }
```

O botão **SEARCH**, tem como finalidade *procurar* por emprestimos no banco de dados com *nome* ou *componente* correspondente ao texto inserido na caixa de texto **txtSearch**, e mostrá-los na tabela.

```

    @FXML
    void btnSearch(ActionEvent event) {
        sqlConnection = new SQLConnection();                    // Conecta com o banco de dados
            
        resultLoans = sqlConnection.getLoan("status", true);    // Seleciona emprestimos em andamento
        listLoans.clear();                                      // Limpa lista de emprestimos
        
        try{
            while(resultLoans.next()){                          // Loop para tratar cada emprestimo da lista 

                // Busca no banco de dados o componente com ID correspondente ao ComponentID do emprestimo

                resultComponents = sqlConnection.getComponent("Id", resultLoans.getInt("componentId"));

                // Verifica se existe na lista um componente ou nome correspondente ao texto inserido

                if(resultLoans.getString("name").toLowerCase().startsWith(txtSearch.getText().toLowerCase())||
                    resultComponents.getString("component").toLowerCase().startsWith(txtSearch.getText().toLowerCase())){
                    
                    // Se existir, seleciona cria um novo objeto com os dados do emprestimo e adiciona na lista 

                    loan = new Loan(resultLoans.getInt("Id"),
                    resultLoans.getString("name"),
                    resultLoans.getInt("componentId"),
                    resultLoans.getInt("quantity"),
                    resultLoans.getDate("loanDate"),
                    resultLoans.getDate("devolutionDate"),
                    resultLoans.getBoolean("status"));
    
                    listLoans.add(loan);
                }
            }

        obsLoans = FXCollections.observableArrayList(listLoans);  // Transforma a lista em Observable
        tblLoans.setItems(obsLoans);                              // Atualiza a tabela
        sqlConnection.close();                                    // Desconecta do banco                        

        }catch(SQLException e){
            System.out.println("Erro ao pesquisar");
            e.printStackTrace();
        }

    }

```

#### HistoryController.java

A classe HistoryController.java é bem semelhante a classe HomeScreenController.java, sua única diferença é não conter o método para devolução, e mostrar **todos** os emprestimos armazenados no banco de dados, isso inclui tanto os *em andamento* como os já *devolvidos*.

```
public class HistoryController implements Initializable{

    @FXML
    private AnchorPane pane;

    // Criando os objetos das colunas da tabela e atribuindo os tipos de objetos que serão exibidos

    @FXML
    private TableColumn<Loan, String> tblColumnComponent = new TableColumn<>();

    @FXML
    private TableColumn<Loan, String> tblColumnDevolution = new TableColumn<>();

    @FXML
    private TableColumn<Loan, String> tblColumnLoan = new TableColumn<>();

    @FXML
    private TableColumn<Loan, Integer> tblColumnQtd = new TableColumn<>();

    @FXML
    private TableColumn<Loan, String> tblColumnStatus = new TableColumn<>();

    @FXML
    private TableColumn<Loan, String> tblColumnName = new TableColumn<>();

    @FXML
    private TableView<Loan> tblHistory;         // Objeto da tabela

    @FXML
    private TextField txtSearch;                // Objeto da caixa de texto

    private List<Loan> listLoans = new ArrayList<>();        // Lista de emprestimos
    private SQLConnection sqlConnection;                     // Objeto para conexão com o banco de dados
    private ResultSet resultLoans, resultComponents;         // Objetos para selecionarmos os dados do banco
    private Loan loan;                                       // Objeto da classe emprestimo
    private ObservableList<Loan> obsLoans;                   // Lista de emprestimos para exibirmos na tabela

    // Método do botão VOLTAR para retornar ao layout HomeScreen

    @FXML
    void btnBack(ActionEvent event) {
        App.changeScene(getClass().getResource("/view/HomeScreen.fxml"), (Stage) pane.getScene().getWindow());

    }

    // Botões padrão para todos os layouts

    @FXML
    void btnComponents(ActionEvent event) {
        App.changeScene(getClass().getResource("/view/Components.fxml"), (Stage) pane.getScene().getWindow());
    }

    @FXML
    void btnHistory(ActionEvent event) {
        App.changeScene(getClass().getResource("/view/History.fxml"), (Stage) pane.getScene().getWindow());
    }

    @FXML
    void btnLoan(ActionEvent event) {
        App.changeScene(getClass().getResource("/view/NewLoan.fxml"), (Stage) pane.getScene().getWindow());
    }

    // Método para procurar nome ou componente correspondente ao texto inserido na caixa de texto 

    @FXML
    void btnSearch(ActionEvent event) {
        sqlConnection = new SQLConnection();
        
        resultLoans = sqlConnection.getLoan();
        listLoans.clear();
        
        try{
            while(resultLoans.next()){

                resultComponents = sqlConnection.getComponent("Id", resultLoans.getInt("componentId"));

                if(resultLoans.getString("name").toLowerCase().startsWith(txtSearch.getText().toLowerCase())||
                    resultComponents.getString("component").toLowerCase().startsWith(txtSearch.getText().toLowerCase())){
                    
                    loan = new Loan(resultLoans.getInt("Id"),
                    resultLoans.getString("name"),
                    resultLoans.getInt("componentId"),
                    resultLoans.getInt("quantity"),
                    resultLoans.getDate("loanDate"),
                    resultLoans.getDate("devolutionDate"),
                    resultLoans.getBoolean("status"));
    
                    listLoans.add(loan);
                }
            }

        obsLoans = FXCollections.observableArrayList(listLoans);
        tblHistory.setItems(obsLoans);

        sqlConnection.close();

        }catch(SQLException e){
            System.out.println("Erro ao pesquisar");
            e.printStackTrace();
        }
    }

    // Método de inicialização do layout

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{        
            tblColumnComponent.setCellValueFactory(new PropertyValueFactory<Loan, String>("component"));
            tblColumnName.setCellValueFactory(new PropertyValueFactory<Loan, String>("name"));
            tblColumnLoan.setCellValueFactory(new PropertyValueFactory<Loan, String>("loanDate"));
            tblColumnDevolution.setCellValueFactory(new PropertyValueFactory<Loan, String>("devolutionDate"));
            tblColumnQtd.setCellValueFactory(new PropertyValueFactory<Loan, Integer>("quantity"));
            tblColumnStatus.setCellValueFactory(new PropertyValueFactory<Loan, String>("status"));

            sqlConnection = new SQLConnection();

            resultLoans = sqlConnection.getLoan();

            while(resultLoans.next()){
                loan = new Loan(resultLoans.getInt("Id"),
                resultLoans.getString("name"),
                resultLoans.getInt("componentId"),
                resultLoans.getInt("quantity"),
                resultLoans.getDate("loanDate"),
                resultLoans.getDate("devolutionDate"),
                resultLoans.getBoolean("status"));

                listLoans.add(loan);
            }

            obsLoans = FXCollections.observableArrayList(listLoans);
            tblHistory.setItems(obsLoans);

            sqlConnection.close();
        } catch (Exception e) {
            System.out.println("Falha ao selecionar dados");
            e.printStackTrace();
        }

    }

}
```

#### ComponentsController.java

A classe ComponentsController.java também é semelhante às outras 2 citadas acima, sua diferença é mostrar na tabela os *componentes* que estão cadastrados no banco de dados, sua quantidade disponível e emprestada, além de também conter um método para o botão **NOVO COMPONENTE**, que muda o layout para **NewComponent.fxml**.

```
public class ComponentsController implements Initializable{

    @FXML
    private AnchorPane pane;

    // Criando os objetos das colunas da tabela e atribuindo os tipos de objetos que serão exibidos

    @FXML
    private TableColumn<Component, String> tblColumnComponent = new TableColumn<>();

    @FXML
    private TableColumn<Component, Integer> tblColumnUnavailable = new TableColumn<>();

    @FXML
    private TableColumn<Component, Integer> tblColumnAvailable = new TableColumn<>();

    @FXML
    private TableView<Component> tblComponents;            // Objeto para a tabela 

    @FXML
    private TextField txtSearch;                           // Objeto da caixa de texto
    
    private List<Component> listComponents = new ArrayList<>();     // Lista de componentes
    private SQLConnection sqlConnection;                            // Objeto para conexão com o banco de dados
    private ResultSet result;                                       // Objeto para seleção dos dados do banco
    private ObservableList<Component> obsComponents;         // Lista de componenetes para exibirmos na tabela

    // Método do botão VOLTAR para retornar ao layout HomeScreen

    @FXML
    void btnBack(ActionEvent event) {
        App.changeScene(getClass().getResource("/view/HomeScreen.fxml"), (Stage) pane.getScene().getWindow());
    }

    // Métodos dos botões padrão

    @FXML
    void btnComponents(ActionEvent event) {
        App.changeScene(getClass().getResource("/view/Components.fxml"), (Stage) pane.getScene().getWindow());
    }

    @FXML
    void btnHistory(ActionEvent event) {
        App.changeScene(getClass().getResource("/view/History.fxml"), (Stage) pane.getScene().getWindow());
    }

    @FXML
    void btnLoan(ActionEvent event) {
        App.changeScene(getClass().getResource("/view/NewLoan.fxml"), (Stage) pane.getScene().getWindow());
    }

    // Método do botão NOVO COMPONENTE para iniciar o layout NewComponent.fxml

    @FXML
    void btnNewComponent(ActionEvent event) {
        App.changeScene(getClass().getResource("/view/NewComponent.fxml"), (Stage) pane.getScene().getWindow());
    }

    // Método para procurar nome ou componente correspondente ao texto inserido na caixa de texto

    @FXML
    void btnSearch(ActionEvent event) {
        sqlConnection = new SQLConnection();
        
        result = sqlConnection.getComponent();
        listComponents.clear();
        try{
            while(result.next()){
                if(result.getString("component").toLowerCase().startsWith(txtSearch.getText().toLowerCase())){

                    listComponents.add(new Component(result.getInt("id"), result.getString("component"),result.getInt("qtdAvailable"), result.getInt("qtdUnavailable")));

                }
            }

        obsComponents = FXCollections.observableArrayList(listComponents);
        tblComponents.setItems(obsComponents);

        sqlConnection.close();

        }catch(SQLException e){
            System.out.println("Erro ao pesquisar");
            e.printStackTrace();
        }
    }

    // Método de inicialização do layout

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tblColumnComponent.setCellValueFactory(new PropertyValueFactory<Component, String>("component"));
        tblColumnAvailable.setCellValueFactory(new PropertyValueFactory<Component, Integer>("qtdAvailable"));
        tblColumnUnavailable.setCellValueFactory(new PropertyValueFactory<Component, Integer>("qtdUnavailable"));

        sqlConnection = new SQLConnection();
        
        result = sqlConnection.getComponent();

        try {
            while(result.next()){

                listComponents.add(new Component(result.getInt("id"), result.getString("component"),result.getInt("qtdAvailable"), result.getInt("qtdUnavailable")));
            }
        } catch (SQLException e) {
            System.out.println("Falha ao selecionar dados do banco");
            e.printStackTrace();
        }

        obsComponents = FXCollections.observableArrayList(listComponents);
        tblComponents.setItems(obsComponents);

        sqlConnection.close();
    }

}
```

#### NewComponentController.java

A classe NewComponent.java é a mais simples do projeto. Ela contém apenas 2 caixas de texto e um botão, além dos botões padrões, sua função é *adicionar* um *novo componente* no banco de dados. Na classe também adicionamos um objeto tipo **Alert** para mostar um aviso na tela caso os dados não forem preenchidos.

```
public class NewComponentController {

    @FXML
    private AnchorPane pane;

    @FXML
    private TextField txtComponent;     // Caixa de texto para nome do componente

    @FXML
    private TextField txtQtd;           // Caixa de texto para a quantidade do componente

    SQLConnection sqlConnection;        // Conexão com banco de dados
    Alert alert;                        // Objeto de alerta

    // Método do botão NOVO COMPONENTE 

    @FXML
    void btnAddComponent(ActionEvent event) {

         // Verifica se os dados foram digitados

        if(!txtComponent.getText().equals("") && !txtQtd.getText().equals("")){ 

            sqlConnection = new SQLConnection();        // Conecta com o banco

            // Insere o componente com base nos dados digitados

            sqlConnection.insertComponent(txtComponent.getText(), Integer.parseInt(txtQtd.getText()), 0);
            
            sqlConnection.close();          // Desconecta do banco de dados

            // Volta pra o layout Components.java

            App.changeScene(getClass().getResource("/view/Components.fxml"), (Stage) pane.getScene().getWindow());
        
            // Caso dados não forem preenchidos

        }else{
            alert = new Alert(Alert.AlertType.WARNING);             // Cria um novo alerta
            alert.setTitle("AVISO!");                               // Adiciona título ao alerta
            alert.setHeaderText("AVISO!");                          // Adiciona cabeçalho ao alerta
            alert.setContentText("Preencha todos os campos!");      // Adiciona conteúdo ao alerta
            alert.showAndWait();                                    // Mostra o alerta
        }
    }

    // Método do botão VOLTAR para retornar ao layout Components.fxml

    @FXML
    void btnBack(ActionEvent event) {
        App.changeScene(getClass().getResource("/view/Components.fxml"), (Stage) pane.getScene().getWindow());

    }

    // Métodos dos botões padrão

    @FXML
    void btnComponents(ActionEvent event) {
        App.changeScene(getClass().getResource("/view/Components.fxml"), (Stage) pane.getScene().getWindow());
    }

    @FXML
    void btnHistory(ActionEvent event) {
        App.changeScene(getClass().getResource("/view/History.fxml"), (Stage) pane.getScene().getWindow());
    }

    @FXML
    void btnLoan(ActionEvent event) {
        App.changeScene(getClass().getResource("/view/NewLoan.fxml"), (Stage) pane.getScene().getWindow());
    }

}
```

#### NewLoanController.java

A classe NewLoanController.java é responsável por adicionar um **novo empréstimo** ao banco de dados. Ela contém 2 tabelas, uma delas mostra os componentes disponíveis para empréstimo (tabela 1), e a outra os componentes a serem emprestados pelo usuário (tabela 2). O usuário poderá selecionar na tabela de *componentes disponíveis* o componente e digitar na caixa de texto a *quantidade* que queira emprestar, além de digitar também seu *nome* na caixa de texto no topo do layout. A classe também contém os objetos para que seja possível pesquisar por conmponentes na tabela, e um objeto tipo *Alert* para mostrar um aviso caso os dados não forem preenchidos.

```

public class NewLoanController implements Initializable{

    @FXML
    private AnchorPane pane;

    @FXML
    private TableView<Component> tblAvailableComponents;        // Tabela componentes disponíveis

    // Criando oS objetos das colunas da tabela de componentes disponíveis e atribuindo os tipos de objetos que serão exibidos

    @FXML
    private TableColumn<Component, String> tblColumnAvailableComponents;

    @FXML
    private TableColumn<Component, Integer> tblColumnAvailableQtd;

    private TableView<Component> tblLoanComponents;               // Tabela de componentes a serem emprestados

    // Criando oS objetos das colunas da tabela de componentes a serem emprestados e atribuindo os tipos de objetos que serão exibidos

    @FXML
    private TableColumn<Component, String> tblColumnLoanComponents;

    @FXML
    private TableColumn<Component, Integer> tblColumnLoanQtd;
    
    @FXML
    private TextField txtQtd;               // Caixa de texto para a quantidade componentes

    @FXML
    private TextField txtSearch;            // Caixa de texto para pesquisa

    @FXML
    private TextField txtName;               // Caixa de texto para o nome

    private List<Component> listComponents = new ArrayList<>();     // Lista de componentes
    private List<Component> listLoanComponents = new ArrayList<>(); // Lista de componentes a serem emprestados
    private SQLConnection sqlConnection;                            // Conexão com banco de dados
    private ResultSet result;                                       // Objeto para seleção dos dados do banco
    private ObservableList<Component> obsComponents;       // Lista de componenetes para exibirmos na tabela
    private int selectedItem;                          // Variável para armazenar o item selecionado na tabela
    private Component component;                       // Objeto da classe componente
    private Alert alert;                               // Objeto para aviso

```

Nessa classe, temos também os botões padrões já citados anteriormente, juntamente com 

```
    @FXML
    void btnBack(ActionEvent event) {
        App.changeScene(getClass().getResource("/view/HomeScreen.fxml"), (Stage) pane.getScene().getWindow());

    }

    @FXML
    void btnComponents(ActionEvent event) {
        App.changeScene(getClass().getResource("/view/Components.fxml"), (Stage) pane.getScene().getWindow());
    }

    @FXML
    void btnHistory(ActionEvent event) {
        App.changeScene(getClass().getResource("/view/History.fxml"), (Stage) pane.getScene().getWindow());
    }

    @FXML
    void btnLoan(ActionEvent event) {
        App.changeScene(getClass().getResource("/view/NewLoan.fxml"), (Stage) pane.getScene().getWindow());
    }
```

Os botões específicos da classe são os botões **ADICIONAR**, e **REALIZAR EMPRÉSTIMO**. Para o botão **ADICIONAR**, foi criada uma lógica para inserir o componentes selecionado da tabela 1 na tabela 2, juntamente com a quantidade, inserida na caixa de texto. Também foi adicionado um método para armazenar na variável *selectedItem* o Index do componente selecionado na tabela.

```
    @FXML
    void btnAddComponent(ActionEvent event) {

        // Cria um objeto para o componente selecionado

        component = tblAvailableComponents.getItems().get(selectedItem);

        // Adiciona o componente na lista de componentes para a tabela 2

        listLoanComponents.add(new Component(component.getId(), component.getComponent(), Integer.parseInt(txtQtd.getText()), 0));

        // Atualiza a tabela

        obsComponents = FXCollections.observableArrayList(listLoanComponents);
        tblLoanComponents.setItems(obsComponents);

    }

    @FXML
    void tblClick(MouseEvent event) {
        selectedItem = tblAvailableComponents.getSelectionModel().getSelectedIndex();
    }
```

Para o botão **REALIZAR EMPRÉSTIMO**, foi criada uma lógica para criar um novo empréstimo para cada componente na tabela 2. Caso algum dado não tenha sido preenchido, o objeto de *Alert* mostra o aviso na tela.

```
    void btnMakeLoan(ActionEvent event) {

        if(!listLoanComponents.isEmpty() && !txtName.getText().equals("")){ //Checa se os dados foram inseridos
            
            sqlConnection = new SQLConnection();             // Conecta com o banco de dados          

            // Loop para adicionar um novo empréstimo no banco para cada componente na tabela 2 

            for(int c = 0; c<listLoanComponents.size(); c++){   
                try {

                    // Adiciona os emprestimos 

                    System.out.println("ADICIONANDO COMPONENTES");
                    sqlConnection.insertLoan(txtName.getText(), listLoanComponents.get(c).getId(), Integer.parseInt(txtQtd.getText()),  new Date(new java.util.Date().getTime()), true);
                    result = sqlConnection.getComponent("Id", listLoanComponents.get(c).getId());

                    // Atualiza os dados dos componentes que foram emprestados

                    System.out.println("ATUALIZANDO COMPONENTES");
                    while(result.next()){
                        sqlConnection.updateComponent("Id", listLoanComponents.get(c).getId(), "qtdAvailable", result.getInt("qtdAvailable") - Integer.parseInt(txtQtd.getText()));
                        sqlConnection.updateComponent("Id", listLoanComponents.get(c).getId(), "qtdUnavailable", result.getInt("qtdUnavailable") + Integer.parseInt(txtQtd.getText()));

                    }
                    
                } catch (NumberFormatException | SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            System.out.println("EMPRESTIMO REALIZADO");
            sqlConnection.close();                              // Desconecta do banco de dados

            // Volta para a HomeScreen

            App.changeScene(getClass().getResource("/view/HomeScreen.fxml"), (Stage) pane.getScene().getWindow());

        }else{
            alert = new Alert(Alert.AlertType.WARNING);              // Cria um novo alerta
            alert.setTitle("AVISO!");                               // Adiciona título ao alerta
            alert.setHeaderText("AVISO!");                          // Adiciona cabeçalho ao alerta

             // Adiciona conteúdo ao alerta

            alert.setContentText("Preencha todos os campos e selecione os componentes desejados!"); 

            alert.showAndWait();                                    // Mostra o alerta
            
        }

    }
```

Para finalizar a classe, temos os métodos **Search()** e o método **Initialize**. Eles são os mesmos das classes anteriores. O método **Search()** tem como função procurar um componente na tabela 1, e o método **Initialize** adiciona os itens assim que o layout é iniciado.

```
    @FXML
    void btnSearch(ActionEvent event) {
        sqlConnection = new SQLConnection();        
        
        result = sqlConnection.getComponent();    
        listComponents.clear();                     

        // Procura o nome do componente correspondente ao texto inserido na caixa de texto

        try{
            while(result.next()){
                if(result.getString("component").toLowerCase().startsWith(txtSearch.getText().toLowerCase())){
                    
                    listComponents.add(new Component(result.getInt("id"), result.getString("component"),result.getInt("qtdAvailable"), result.getInt("qtdUnavailable")));

                }
            }

        obsComponents = FXCollections.observableArrayList(listComponents);
        tblAvailableComponents.setItems(obsComponents);

        sqlConnection.close();

        }catch(SQLException e){
            System.out.println("Erro ao pesquisar");
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tblColumnAvailableComponents.setCellValueFactory(new PropertyValueFactory<Component, String>("component"));
        tblColumnAvailableQtd.setCellValueFactory(new PropertyValueFactory<Component, Integer>("qtdAvailable"));

        tblColumnLoanComponents.setCellValueFactory(new PropertyValueFactory<Component, String>("component"));
        tblColumnLoanQtd.setCellValueFactory(new PropertyValueFactory<Component, Integer>("qtdAvailable"));


        sqlConnection = new SQLConnection();
        
        result = sqlConnection.getComponent();

        try {
            while(result.next()){
                listComponents.add(new Component(result.getInt("id"), result.getString("component"),result.getInt("qtdAvailable"), result.getInt("qtdUnavailable")));
            }
        } catch (SQLException e) {
            System.out.println("Falha ao selecionar dados do banco");
            e.printStackTrace();
        }

        obsComponents = FXCollections.observableArrayList(listComponents);
        tblAvailableComponents.setItems(obsComponents);

        sqlConnection.close();
    }
```

## Configurações no Raspberry PI

Antes de executar o projeto no Raspberry PI, é necessário realizar algumas configurações.

### JDK

Para instalarmos a JDK do java no Rasberry, precisamos executar os seguintes comandos.

Atualizar pacotes do Raspberry:
```
sudo apt-get update
sudo apt-get upgrade
```

Instalar JDK default:

```
sudo apt install default-jdk
```

Instalar JRE 13 da Bellsoft:

```
wget https://download.bell-sw.com/java/13/bellsoft-jdk13-linux-arm32-vfp-hflt.deb 
sudo apt-get install ./bellsoft-jdk13-linux-arm32-vfp-hflt.deb 
```

Selecionando JDK padrão:

```
sudo update-alternatives --config java

*Digitar número correspondente ao JDK da Bellsooft e precionar Enter

sudo update-alternatives --config javac

*Digitar número correspondente ao JDK da Bellsooft e precionar Enter
```

Ao executar os comandos acima, o Raspberry irá atualizar seus pacotes, instalar o JDK padrão e logo após instalar o JDK da Bellsoft. Precisamos desse JDK para que seja possível rodarmos o JavaFX. Com isso, o Java estará configurado para rodar a interface.

### Configurar Tela

Como o projeto foi idealizado com o display em formato retrato, precisamos rotacionar a tela do Rasberry e configurar o touch do display, fazemos isso com os seguintes comandos.

Rotacionar tela:

Clique na framboesa -> Screen Configuration -> Botão direito no quadrado cinza **HDMI** -> Orientação -> Right

Configurar touch do display:

Leia os IDs dos displays conectados pelo comando:

```
xinput list
```

Após isso, digite o comando:

```
sudo nano /usr/share/X11/xorg.conf.d/40-libinput.conf
```

E ajuste a seção "Section InputClass" com o "Identifier libinput touchpad catchall" da seguinte forma:

```
Section "InputClass" 
        Identifier "libinput touchpad catchall" 
        MatchIsTouchscreen "on" 
        Option "CalibrationMatrix" "0 1 0 -1 0 1 0 0 1" 
        MatchDevicePath "/dev/input/event*" 
        Driver "libinput" 
EndSection
```

Aperte CTRL+X, S e ENTER para sair e salvar. Em seguida, de volta ao terminal, digite o comando:

```
sudo nano .profile
```

E adicione as seguintes linhas no final desse arquivo:

```
xinput set-prop "6" --type=float "Coordinate Transformation Matrix" 1 0 0 0 0.5 0 0 0 1
xinput set-prop "7" --type=float "Coordinate Transformation Matrix" 1 0 0 0 0.5 0.5 0 0 1
```

### Teclado Virtual

Como o projeto é um sistema embarcado, precisamos de um teclado virtual, para que não seja necessário a utilização de um teclado externo para inserir os dados. Dessa forma, foi escolhido o teclado virtual **OnBoard**, pelo comando

```
sudo apt install onboard
```


### Configurando StartUp

Por fim, para executar a interface logo na inicialização do Raspberry, precimos criar uma pasta **autostart** no diretório **/.config**, e criar um arquivo *.desktop* para a interface e para o teclado virtual, e editá-los da seguinte maneira:

```
mkdir /home/pi/.config/autostart
sudo nano /home/pi/.config/autostart/onboard.desktop

[Desktop Entry]
Type=Application
Name=Onboard
Exec=onboard

sudo nano /home/pi/.config/autostart/onboard.desktop

[Desktop Entry]
Type=Application
Name=Java
Exec=java -jar [diretório do arquivo .jar da interface]

```

## Referências

Instalar JDK:

https://linuxize.com/post/install-java-on-raspberry-pi/

https://blogs.oracle.com/javamagazine/post/getting-started-with-javafx-on-raspberry-pi


Configurar tela: 

https://www.interelectronix.com/br/raspberry-pi-4-monitor-touchscreen.html#:~:text=Para%20fazer%20isso%2C%20no%20menu,outro%20e%20salve%20as%20configura%C3%A7%C3%B5es

Teclado virtual:

https://www.industrialshields.com/blog/raspberry-pi-for-industry-26/post/top-3-on-screen-virtual-keyboards-for-raspberry-plc-panel-pc-401

Configurar StartUp:

https://learn.sparkfun.com/tutorials/how-to-run-a-raspberry-pi-program-on-startup/method-2-autostart

## Vídeo de demonstração

Link para o video de demonstração: https://youtu.be/PJxt_4aAiNw

## Considerações finais 

Trabalhei nesse projeto durante 1 mês, e durante o desenvolvimento, aprendi muitas coisas relacionadas a  linguagem Java, Banco de dados e Raspberry PI e utilizei novas ferramentas como o TRELLO. Foi muito divertido desenvolve-lo e espero utilizar os conhecimentos adquiridos para criar novos projetos como esse. 


## Me faça uma doação!!!

Gostou do projeto? Me faça uma doação! Assim você me incentiva a aprender mais e criar novos projetos 😃

Você pode utilizar o App do seu banco para ler o QR Code abaixo, ou se preferir, utilizar minha chave pix:

👇👇

0caf9f55-45dd-43a3-865c-70d005770ef7

![donate](/Planejamento/Imagens%20documenta%C3%A7%C3%A3o/donate.jpg)
