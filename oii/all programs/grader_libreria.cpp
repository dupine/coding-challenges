#include <iostream>
#include <vector>
#include <map>
#include <string>
using namespace std;

map<long long int, int> hashtable;

void aggiungi(long long int id) {
    if(hashtable.find(id) != hashtable.end()) hashtable[id] = hashtable[id] + 1;
    else hashtable[id] = 1;
}

void togli(long long int id) {
    hashtable[id] = hashtable[id] - 1;
}

int conta(long long int id) {
    return (hashtable.find(id) == hashtable.end()) ? 0 : hashtable[id];
}


int main(){
	long long int id;
	int n;
	int b;
	char x;
	
	freopen("input.txt","r",stdin);
	freopen("output.txt","w",stdout);
	
	cin>>n;
	b=0;
	for (int i=0;i<n;i++){
		cin>>x>>id;

		if (x =='a'){
			aggiungi(id);
		}
		if (x=='t'){
			togli(id);
		}
		if (x=='c'){
			cout << conta(id) << '\n';
		}
	}
	return 0;
}