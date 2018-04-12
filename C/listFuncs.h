// Name:
// Loginid:
// CSCI 455 PA5
// Spring 2017


//*************************************************************************
// Node class definition 
// and declarations for functions on ListType

// Note: we don't need Node in Table.h
// because it's used by the Table class; not by any Table client code.


#ifndef LIST_FUNCS_H
#define LIST_FUNCS_H
#include <string>
  
using namespace std;

struct Node {
  string key;
  int value;

  Node *next;

  Node(const string &theKey, int theValue);

  Node(const string &theKey, int theValue, Node *n);
};


typedef Node * ListType;

//*************************************************************************
//add function headers (aka, function prototypes) for your functions
//that operate on a list here (i.e., each includes a parameter of type
//ListType or ListType&).  No function definitions go in this file.

void emptyList(ListType &list);

void add(ListType &list, string name, int score);

void update(ListType &list, string target, int newscore);

bool remove(ListType &list, string target);

Node* lookup(ListType list, string target);

void printAll(ListType list);


// keep the following line at the end of the file
#endif
