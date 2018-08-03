namespace java example.thrift

typedef i32 int // We can use typedef to get pretty names for the types we are using
typedef i64 long

/*
	User Profile struct
*/
struct Profile {
	1: long id,
	2: string name,
	3: int age
}

enum ErrorType {
    SUCCESS = 0,       
    NOT_FOUND = -1,
} 

struct TGetProfileResult 
{
	1: required i64 err,
	2: optional list<Profile> profile,	
}

service ProfileService {
	TGetProfileResult getAll(),
	TGetProfileResult get(1:long id),
	bool insert(1:Profile profile),
	bool update(1:Profile profile),
	bool remove(1:long id),
}

/*
service MultiplicationService
{
        int multiply(1:int n1, 2:int n2),
        int add(1:int n1, 2:int n2),
}
*/