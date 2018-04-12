// Name:Rohan Desai 
// Loginid:rohannde
// CSCI 455 PA5
// Spring 2017


#include <iostream>
#include <string>
#include <cassert>

#include "listFuncs.h"

using namespace std;

Node::Node(const string &theKey, int theValue)
{
  key = theKey;
  value = theValue;
  next = NULL;
}

Node::Node(const string &theKey, int theValue, Node *n) 
{
  key = theKey;
  value = theValue;
  next = n;
}




//*************************************************************************
// put the function definitions for your list functions below

bool remove(ListType &list, string target) {
  bool found = true;
  if (list == NULL) {  // no elements in list
    found=false;
	return found;
  }
  
  if (list->next == NULL) {   // one element in list
	if(list->key==target)
	{
		delete list;
    	list = NULL;
    	return found;
	}
  }
  
   // we know here that the list has at least 2 elements

  Node * first = list;  // points to first element
  Node *second = list ->next;    // points to second element
  
  while (second->next != NULL) {  // while second is not the last element
    if(first->key==target)	//deletes if first element in the list of more than one element matches the target
    {
		list=first->next;
    	delete first;
    	return found;
	}
	else if(second->key==target)
	{
		first->next=second->next;
		delete second;
		return found;		
	}
	first=first->next;
	second=second->next;
  }
  found=false;
  return found;
}

void emptyList(ListType &list){
  	list = NULL;
}

void add(ListType & list, string name, int score)
{
	Node *newentry = new Node(name, score, list);
	list = newentry;
}



Node* lookup(ListType list, string target){
  	if (list == NULL) {  // no elements in list
		return NULL;
  	}  
  	while(list!=NULL){
	  if(list->key==target){
		return list;
	  }
	  list=list->next;
	}
	
}

void update(ListType &list, string target, int newscore){
	Node *location=lookup(list,target);
	if(location==NULL){
		cout<<" Name not found in the list ";
	}
	else
	{
		cout<<"Original Value: ";
		cout<<location->key<<"-"<<location->value;
		location->value=newscore;
		cout<<"Updated Value: ";
		cout<<location->key<<"-"<<location->value;
	}
	
}

void printAll(ListType list){
	
	if (list == NULL) {
    	cout << "<empty>";
  	}

	Node *p = list;
	while (p != NULL) {
	  	cout << p->key << " : "<< p->value;
    	p = p->next;
  	}
  	cout << endl;
}

