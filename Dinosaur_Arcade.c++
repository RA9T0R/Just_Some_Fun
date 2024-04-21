//This C++ Program is the Basic game select it have 
//CalculatorGame , QuizGame , RandomGame , rpsGame , XOGame

#include <iostream>
#include <ctime>
using namespace std;

int select;

int Playagain(); //ask user want to playagain ? press y is yes and other buttom is not
void play(); //Show name game and ask user select that user want to play

void CalculatorGame(); 
void QuizGame();
void RandomGame();

void rpsGame();
char getUserChoice();
char getComputerChoice();
void showChoice(char choice);
void ChooseWinner(char player,char computer);

void XOGame();
void drawBorad(char *spaces);
void playerMove(char *spaces,char player);
void computerMove(char *spaces,char computer);
bool checkWinner(char *spaces,char player,char computer);
bool checkTie(char *spaces);

int main(){
    bool PLAY=true;
    cout << "================================================================\n";
    cout << "================== Welcome To Dinosaur Arcade ==================\n";
    while (PLAY){
        play();
        switch (select){
            case 1 : CalculatorGame();
                    break;
            case 2 : QuizGame();
                    break;
            case 3 : RandomGame();
                    break;
            case 4 : rpsGame();
                    break;
            case 5 : XOGame();
                    break;
        }
        if(!Playagain()){PLAY=false;}
    }
    return 0;
}

void play(){
    do{
        cout << "================================================================\n";
        cout << "1. Basic Calculator\n";
        cout << "2. Quiz Game\n";
        cout << "3. Random Number Game\n";
        cout << "4. Rock Paper Scissors Game\n";
        cout << "5. XO Game\n";
        cout << "Plase Select Game You Want To Play (1,2,3,4,5) : ";
        cin >> select;
    }while (select <= 0 || select >= 6 );
}
int Playagain(){
    char x;
    cout << "Do you want to play again ? (Y to play again) : ";
    cin >> x;
    if(x=='Y' || x=='y')return 1;
    else return 0;
}

void CalculatorGame(){
    char OP;
    double num1,num2,result;
    cout<<"-------------------- CALCULATOR --------------------" <<"\n";
    cout<<"Enter either (+,-,*,/) : ";
    cin >> OP;
    cout<<"Enter Number 1 : ";
    cin >> num1;
    cout<<"Enter Number 2 : ";
    cin >> num2;

    switch(OP){
        case '+' :
            result=num1+num2;
            cout<<"result : "<<result<<endl;
            break;
        case '-' :
            result=num1-num2;
            cout<<"result : "<<result<<endl;
            break;
        case '*' :
            result=num1*num2;
            cout<<"result : "<<result<<endl;
            break;
        case '/' :
            result=num1/num2;
            cout<<"result : "<<result<<endl;
            break;
        default : cout<<"That wasn't a valid oparator\n"; break;
    }
    cout<<"----------------------------------------------------" <<"\n";
}
void QuizGame(){
    string questions[] = 	{"1. What year was C++ created?: ",
								 "2. Who invented C++?: ",
								 "3. What is the predecessor of C++?: ",
								 "4. Is the Earth flat?"};

	string options[][4] = 	{{"A. 1969", "B. 1975", "C. 1985", "D. 1989"},
								{"A. Guido van Rossum", "B. Bjarne Stroustrup", "C. John Carmack", "D. Mark Zuckerburg"},
								{"A. C", "B. C+", "C. C--", "D. B++"},
								{"A. Yes", "B. No", "C. Sometimes", "D. what's Earth?"}};

	char answerKey[] = {'C', 'B', 'A', 'B'};

    int size = sizeof(questions) / sizeof(questions[0]);
    char guess;
    int score=0;
    for(int i = 0 ; i < size ; i++){
        cout << "***************************\n";
        cout << questions[i] << endl;
        cout << "***************************\n";

        for(int j = 0 ; j < sizeof(options[i]) / sizeof(options[i][0]) ; j++){
            cout << options[i][j] << endl;
        }
        cout << "--- Enter guess of questions "<<i+1<<" : ";
        cin >> guess ; 
        guess = toupper(guess);

        if(guess == answerKey[i]){
            cout << "CORRECT\n";
            score++;
        }else{
            cout << "WRONG!\n";
            cout << "Answer : " << answerKey[i] << endl;
        }
    }

    cout << "***************************\n";
    cout << "*         RESULTS         *\n";
    cout << "***************************\n";
    cout << "You Score is "<<score<<endl;
    cout << "Number of QUESTIONS : "<<size<<endl;
    cout << "Score : "<<(score/(double)size)*100<<"%"<<endl;
    cout<<"----------------------------------------------------" <<"\n";
}
void RandomGame(){
    int num;
    int guess;
    int tries=0;

    srand(time(0));
    num=(rand()%100)+1;
    cout << "-------------------- NUMBER GUESSING -------------------- \n";
    do{
        cout << "Enter a guess between (1-100) : ";
        cin >> guess;
        tries+=1;
        if(guess>num) {cout<<"Too High!\n";}
        else if(guess<num) {cout<<"Too Low!\n";}
        else {cout<<"CORRECT! Number of tries : " << tries;}
    }while (guess!=num);

    cout<<"\n----------------------------------------------------" <<"\n";
}

void rpsGame(){
    char player,computer;

    player=getUserChoice();
    cout<<"You Choice : ";
    showChoice(player);

    computer=getComputerChoice();
    cout<<"Computer Choice : ";
    showChoice(computer);

    ChooseWinner(player,computer);
    cout<<"----------------------------------------------------" <<"\n";
}
char getUserChoice(){
    char player;
    cout<<"-------------------- Rock-Paper-Scissors Game!-------------------- \n";
    do{
        cout<<"Choose one of the following\n";
        cout<<"*************************\n";
        cout<<"'r' for rock\n";
        cout<<"'p' for Paper\n";
        cout<<"'s' for Scissors\n";
        cin>>player;
    }while(player != 'r' && player != 'p' && player != 's');
    
    return player;
}
char getComputerChoice(){
    srand(time(0));
    int num = (rand() % 3 )+1;
    switch (num){
    case 1 : return 'r';
    case 2 : return 'p';
    case 3 : return 's';
    }
    return 0;
}
void showChoice(char choice){
    switch (choice){
    case 'r' : cout << "Rock\n";
               break;
    case 'p' : cout << "Paper\n";
               break;
    case 's' : cout << "Scissors\n";
               break;
    }
}
void ChooseWinner(char player,char computer){
    switch (player){
    case 'r' : if(computer == 'r'){
                    cout << "It's a tie\n";
                }else if(computer == 'p'){
                    cout << "You lose!\n";
                }else{
                    cout << "You Win!\n";
                }
                break;
    case 'p' : if(computer == 'r'){
                    cout << "You Win!\n";
                }else if(computer == 'p'){
                    cout << "It's a tie\n";
                }else{
                    cout << "You lose!\n";
                }
                break;
    case 's' : if(computer == 'r'){
                    cout << "You lose!\n";
                }else if(computer == 'p'){
                    cout << "You Win!\n";
                }else{
                    cout << "It's a tie\n";
                }
                break;
    default:break;
    }
}

void XOGame(){
    cout << "-------------------- XO GAME -------------------- \n";
    char spaces[9] = {' ',' ',' ',' ',' ',' ',' ',' ',' '};
    char player = 'X';
    char computer = 'O';
    bool running = true ;

    drawBorad(spaces);

    while (running){
        playerMove(spaces,player);
        drawBorad(spaces); 
        if(checkWinner(spaces,player,computer)){
            running = false;
            break;
        }
        else if(checkTie(spaces)){
            running = false;
            break;
        }

        computerMove(spaces,computer);
        drawBorad(spaces);  
        if(checkWinner(spaces,player,computer)){
            running = false;
            break;
        }
        else if(checkTie(spaces)){
            running = false;
            break;
        }
    }
    cout<<"Thank For Playing! :)\n";
    cout<<"----------------------------------------------------" <<"\n";
}
void drawBorad(char *spaces){
    cout << "\n";
    cout << "     |     |     \n";
    cout << "  "<<spaces[0]<<"  |  "<<spaces[1]<<"  |  "<<spaces[2]<<"  \n";
    cout << "_____|_____|_____\n";
    cout << "     |     |     \n";
    cout << "  "<<spaces[3]<<"  |  "<<spaces[4]<<"  |  "<<spaces[5]<<"  \n";
    cout << "_____|_____|_____\n";
    cout << "     |     |     \n";
    cout << "  "<<spaces[6]<<"  |  "<<spaces[7]<<"  |  "<<spaces[8]<<"  \n";
    cout << "     |     |     \n";
    cout << "\n";
}
void playerMove(char *spaces,char player){
    int number;
    do{
        cout << "Enter a spot to place a marker (1-9) : ";
        cin >> number;
        number--;
        if(spaces[number]==' '){
            spaces[number] = player;
            break;
        }
    }while(!number > 0 || !number < 8);
}
void computerMove(char *spaces,char computer){
    int number;
    srand(time(0));
    while (true){
        number=rand()%9;
        if(spaces[number]==' '){
            spaces[number] = computer;
            break;
        }
    }
    
}
bool checkWinner(char *spaces,char player,char computer){

    if((spaces[0] != ' ') && spaces[0] == spaces[1] && spaces[1] == spaces[2]){
        spaces[0] == player ? cout << "YOU WIN!\n" : cout << "YOU LOSE!\n";
    }
    else if((spaces[3] != ' ') && spaces[3] == spaces[4] && spaces[4] == spaces[5]){
        spaces[3] == player ? cout << "YOU WIN!\n" : cout << "YOU LOSE!\n";
    }
    else if((spaces[6] != ' ') && spaces[6] == spaces[7] && spaces[7] == spaces[8]){
        spaces[6] == player ? cout << "YOU WIN!\n" : cout << "YOU LOSE!\n";
    }
    else if((spaces[0] != ' ') && spaces[0] == spaces[3] && spaces[3] == spaces[6]){
        spaces[0] == player ? cout << "YOU WIN!\n" : cout << "YOU LOSE!\n";
    }
    else if((spaces[1] != ' ') && spaces[1] == spaces[4] && spaces[4] == spaces[7]){
        spaces[1] == player ? cout << "YOU WIN!\n" : cout << "YOU LOSE!\n";
    }
    else if((spaces[2] != ' ') && spaces[2] == spaces[5] && spaces[5] == spaces[8]){
        spaces[2] == player ? cout << "YOU WIN!\n" : cout << "YOU LOSE!\n";
    }
    else if((spaces[0] != ' ') && spaces[0] == spaces[4] && spaces[4] == spaces[8]){
        spaces[0] == player ? cout << "YOU WIN!\n" : cout << "YOU LOSE!\n";
    }
    else if((spaces[2] != ' ') && spaces[2] == spaces[4] && spaces[4] == spaces[6]){
        spaces[2] == player ? cout << "YOU WIN!\n" : cout << "YOU LOSE!\n";
    }
    else{
        return false;
    }

    return true;
}
bool checkTie(char *spaces){

    for(int i = 0 ; i < 9 ; i++ ){
        if(spaces[i]==' '){
            return false;
        }
    }
    cout<<"IT'S A TIE!\n";
    return true;
}

