#include <iostream>
using namespace std;

typedef struct{
    string title[10];
    
    string description[10];
    int due_date[10];
    string priority[10];
    bool status[10] = {false};
}TODO;

int select;
void DO();
int DOagain();

void ViewT(TODO &user);
void AddT(TODO &user);
void EditT(TODO &user);
void MarkT(TODO &user);
void DeleteT(TODO &user);
void SortT(TODO &user);
void FilterT(TODO &user);

int main(){
    TODO user;
    fill(user.title , user.title+10 ,"lette");
    fill(user.description , user.description+10 ,"11 letter..");
    fill(user.due_date , user.due_date+10 ,0);
    fill(user.priority , user.priority+10 ,"height..");

    bool PLAY=true;
    cout << "================================================================\n";
    cout << "========================== TO-DO-LIST ==========================\n";
    while (PLAY){
        DO();
        switch (select){
            case 1 : ViewT(user);
                    break;
            case 2 : AddT(user);
                    break;
            case 3 : EditT(user);
                    break;
            case 4 : MarkT(user);
                    break;
            case 5 : DeleteT(user);
                    break;
            case 6 : SortT(user);
                    break;
            case 7 : FilterT(user);
                    break;
        }
        if(!DOagain()){PLAY=false;}
    }
    return 0;
}
void DO(){
    do{
        cout << "================================================================\n";
        cout << "What Do u want to do ? : \n";
        cout << "1.View Tasks\n";
        cout << "2.Add Task\n";
        cout << "3.Edit Task\n";
        cout << "4.Mark as Completed\n";
        cout << "5.Delete Task\n";
        cout << "6.Sort Tasks\n";
        cout << "7.Filter Tasks\n";
        cout << "Plase Select Choice You Want To Do (1,2,3,4,5,6,7) : ";
        cin >> select;
    }while (select <= 0 || select >= 8 );
}
int DOagain(){
    char x;
    cout << "Do you want to do again ? (Y to do again) : ";
    cin >> x;
    if(x=='Y' || x=='y')return 1;
    else return 0;
}

void ViewT(TODO &user){
    cout << "==================================== TO-DO-LIST ====================================\n";
    cout << "|    title    |     description     |    due_date   |    priority   |     status   |\n";
    cout << "====================================================================================\n";
    for(int i = 0 ; i < 10 ; i++ ){
        if(user.title[i] != "lette"){
            cout << "|    "<< user.title[i] <<"    |     "<< user.description[i] <<"     |    "<< user.due_date[i] <<"   |    "<< user.priority[i] <<"   |     "<< user.status[i] <<"   |\n";
        }
    }
    cout << "====================================================================================\n";   
}
void AddT(TODO &user){
    string title;
    string description;
    int due_date;
    string priority;
    do{
        cout << "Title (<=5 letter) : ";
        cin >> title;
    }while(title.length() > 5);
    do{
        cout << "Description (<=11 letter) : ";
        cin >> description;
    }while(description.length() > 11);
    do{
        cout << "Due_Date (number such as 120167): ";
        cin >> due_date;
    }while(due_date > 999999);
    do{
        cout << "Priority (Height(1) / Medium(2) / Low(3)): ";
        cin >> priority;
        if(priority == "1"){priority="Height..";}
        else if(priority == "2"){priority="Medium..";}
        else if(priority == "3"){priority="Low.....";}
    }while(priority.length() > 8);
    
    for(int i = 0 ; i < 10 ; i++ ){
        if(user.title[i] == "lette") {
            user.title[i] = title;
            user.description[i] = description;
            user.due_date[i] = due_date;
            user.priority[i] = priority;
            break;
        }
    }
    cout << "+++++ DONE +++++\n";
}
void EditT(TODO &user){
    int x,y;
    int NEW;
    string New;
    string title;
    string description;
    string due_date;
    string priority;
    cout << "What Tasks u want to edit _type number(1-10)_ : ";
    cin >> x;
    cout << "What Data column u want to edit _type number(1-4)_ : ";
    cin >> y;
    if(y==3){
        cout << "OK what is new data : ";
        cin >> NEW;
    }else{
        cout << "OK what is new data : ";
        cin >> New; 
    }
    switch (y){
        case 1 : user.title[x-1] = New; break;
        case 2 : user.description[x-1] = New; break;
        case 3 : user.due_date[x-1] = NEW; break;
        case 4 : user.priority[x-1] = New; break;
    }
    cout << "+++++ DONE +++++\n";
}
void MarkT(TODO &user){
    int x;
    cout << "What Tasks do u want to make it as Completed _type number(1-10)_ : ";
    cin >> x;
    user.status[x-1] = true;
    cout << "+++++ DONE +++++\n";
}
void DeleteT(TODO &user){
    int x;
    cout << "What Tasks do u want to Delete it _type number(1-10)_ : ";
    cin >> x;
    user.title[x-1] = "lette";
}
void SortT(TODO &user){
    int x;
    cout << "Sort min to max or max to min (1,2) : ";
    cin >> x;
    x--;
    for(int i = 0 ; i < 10 - 1 ; i++ ){
        for(int j = 0 ; j < 10 -1 -i ; j++){
            if(x  ?  user.due_date[j]<user.due_date[j+1]  :   user.due_date[j]>user.due_date[j+1]){
                int temp1 = user.due_date[j];
                user.due_date[j] = user.due_date[j+1];
                user.due_date[j+1] = temp1;

                string temp2 = user.title[j];
                user.title[j] = user.title[j+1];
                user.title[j+1] = temp2;

                string temp3 = user.description[j];
                user.description[j] = user.description[j+1];
                user.description[j+1] = temp3;

                string temp4 = user.priority[j];
                user.priority[j] = user.priority[j+1];
                user.priority[j+1] = temp4;
            }
                bool temp5 = user.status[j];
                user.status[j] = user.status[j+1];
                user.status[j+1] = temp5;
        }
    }
}
void FilterT(TODO &user){
    int x;
    cout << "What Tasks do u want to see _type 2 number(1-10)_ : ";
    cin >> x;
    for(int i = x/10 ; i < 10 ; i++ ){
        if(i == x%10){
            break;
        }
        cout << "|    "<< user.title[i] <<"    |     "<< user.description[i] <<"     |    "<< user.due_date[i] <<"   |    "<< user.priority[i] <<"   |     "<< user.status[i] <<"   |\n";
    }
    cout << "====================================================================================\n";   
}

