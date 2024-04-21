#include <iostream>
#include <vector>
#include <string>

using namespace std;

struct Transaction {
    string type;
    int amount;
};

typedef struct{
    string name[10];
    int accountNum[10]={1001,1002,1003,1004,1005,1006,1007,1008,1009,1010};
    int Balance[10];
    vector<Transaction> transactions[10]; //--------------------------------------
}BANK;

int select;

void DO();

void CreateAccount(BANK &bank);
void DepositFunds(BANK &bank);
void WithdrawFunds(BANK &bank);
void CheckBalance(BANK &bank);
void ViewHistory(BANK &bank);
void Exit(bool &status);

int main(){
    BANK bank;
    fill(bank.name , bank.name+10 ,"Empty");

    bool status=true;
    cout << "====================================================================\n";
    cout << "========================== BANKING SYSTEM ==========================\n";
    while (status){
        DO();
        switch (select){
            case 1 : CreateAccount(bank);
                    break;
            case 2 : DepositFunds(bank);
                    break;
            case 3 : WithdrawFunds(bank);
                    break;
            case 4 : CheckBalance(bank);
                    break;
            case 5 : ViewHistory(bank);
                    break;
            case 6 : Exit(status);
                    break;
        }
    }
    return 0;
}

void DO(){
    do{
        cout << "====================================================================\n";
        cout << "What Do u want to do in our Banking System ? \n";
        cout << "1. Create Account\n";
        cout << "2. Deposit Funds\n";
        cout << "3. Withdraw Funds\n";
        cout << "4. Check Balance\n";
        cout << "5. View Transaction History\n";
        cout << "6. Exit\n\n";
        cout << "Please select an option: ";
        cin >> select;
    }while (select <= 0 || select >=7);
}

void CreateAccount(BANK &bank){
    cout << "\n----- Create Account -----\n";
    for(int i = 0 ; i < 10 ; i++){
        if(bank.name[i] == "Empty"){
            cout << "Enter your name: ";
            cin >> bank.name[i];
            cout << "Enter initial deposit amount: ";
            cin >> bank.Balance[i];
            bank.transactions[i].push_back({"Initial Deposit: +", bank.Balance[i]}); //-------------------
            cout << "Account created successfully! Your account number is: "<<bank.accountNum[i]<<endl;
            return;
        }
    }
    cout << "OURS BANK ACCOUNT IS NOW MAXIMUM SORRY :( \n";
}
void DepositFunds(BANK &bank){
    int number,money;
    cout << "\n----- Deposit Funds -----\n";
    cout << "Enter your account number: ";
    cin >> number;
    cout << "Enter deposit amount: ";
    cin >> money;
    for(int i = 0 ; i < 10 ; i++){
        if(bank.accountNum[i] == number){
            bank.Balance[i]+=money;
            bank.transactions[i].push_back({"Deposit: +", money}); //-------------------
            cout << "Deposit successful! New balance: "<<bank.Balance[i]<<endl;
            break;
        }
    }
}
void WithdrawFunds(BANK &bank){
    int number,money;
    cout << "\n----- Withdraw Funds -----\n";
    cout << "Enter your account number: ";
    cin >> number;
    cout << "Enter Withdrawal amount: ";
    cin >> money;
    for(int i = 0 ; i < 10 ; i++){
        if(bank.accountNum[i] == number){
            if(bank.Balance[i] >= money){
                bank.Balance[i]-=money;
                bank.transactions[i].push_back({"Withdrawal: -", money}); //-------------------
                cout << "Withdrawal successful! New balance: "<<bank.Balance[i]<<endl;
            }else{
                cout << "Insufficient funds!"<<endl;
            }
            break;
        }
    }
}
void CheckBalance(BANK &bank){
    int number;
    cout << "\n----- Check Balance -----\n";
    cout << "Enter your account number: ";
    cin >> number;
    for(int i = 0 ; i < 10 ; i++){
        if(bank.accountNum[i] == number){
            cout << "Your current balance is: "<<bank.Balance[i]<<endl;
            break;
        }
    }
}
void ViewHistory(BANK &bank){
    int number,j=1;
    cout << "\n----- View Transaction History -----\n";
    cout << "Enter your account number: ";
    cin >> number;
    cout << "\nTransaction History:\n";
    for(int i = 0 ; i < 10 ; i++){
        if(bank.accountNum[i] == number){
            for(const Transaction& transaction : bank.transactions[i]) {
                cout << j << ". " <<transaction.type << transaction.amount << endl;
                j++;
            }
            cout << "\nCurrent Balance: "<<bank.Balance[i]<<endl;
            break;
        }
    }
}

void Exit(bool &status){
    cout << "\n++++++++ Exiting the banking system. Goodbye :) ++++++++\n";
    status = false;
}
