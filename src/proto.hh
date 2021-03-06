#include "struct.hh"
#include "macros.hh"
#include <stdarg.h>


/* util.cpp (MLR-MCL codebase) */
void assignClustersToHubs(idxtype*, idxtype*, int, int,
GraphType*);
void dfTraversalMatrix(Matrix*, idxtype, idxtype*, int*, wgttype
minWgt);
void dfTraversal(GraphType*, idxtype, idxtype*, int*);
//void initOptions(Options*);
void mapIndices(idxtype*, idxtype*, int, int);
idxtype* lookForSingletons(GraphType*, int*);
void errexit(char *,...);
#ifndef DMALLOC
int *imalloc(int, const char *);
idxtype *idxmalloc(int, const char *);
float *fmalloc(int, const char *);
int *ismalloc(int, int, const char *);
idxtype *idxsmalloc(int, idxtype, const char *);
//void *GKmalloc(int, char *);
void *GKmalloc(long, const char *);
#endif
void GKfree(void **,...); 
int *iset(int n, int val, int *x);
idxtype *idxset(int n, idxtype val, idxtype *x);
float *sset(int n, float val, float *x);
int iamax(int, int *);
int idxamax(int, idxtype *);
int idxamax_strd(int, idxtype *, int);
int samax(int, float *);
int samax2(int, float *);
int idxamin(int, idxtype *);
int samin(int, float *);
int idxsum(int, idxtype *);
int idxsum_strd(int, idxtype *, int);
void idxadd(int, idxtype *, idxtype *);
int charsum(int, const char *);
int isum(int, int *);
float ssum(int, float *);
float ssum_strd(int n, float *x, int);
void sscale(int n, float, float *x);
float snorm2(int, float *);
float sdot(int n, float *, float *);
void saxpy(int, float, float *, int, float *, int);
void ParallelQSortFloatsInts(wgttype*, idxtype*, int, int);
void ParallelQSortIntsUsingScores(idxtype*, idxtype*, idxtype*,
			int, int);
void ParallelQSort(idxtype*,wgttype*,int,int);
void ParallelQSortInts(idxtype*,idxtype*,int,int);
void QSortIntsUsingInts(idxtype*, idxtype*, int, int);
void ParallelQSortLongs(long*,wgttype*,int,int);
int bsearch_insertPos(idxtype*, int, int, int);
void RandomPermute(int, idxtype *, int);
void permuteDegreeOrder(int, idxtype*, idxtype*);
wgttype RandomSelect(wgttype*, int, int, int);
idxtype RandomSelectInts(idxtype*, int, int, int);
double drand48();
void srand48(long);
int ispow2(int);
void InitRandom(int);
int log2(int);

/* io.cpp (MLR-MCL codebase) */
void ReadMatrix(Matrix *, char*, wgttype threshold=0);
void readMemberships(char*, int, idxtype*, idxtype**, idxtype**,
				idxtype*);
idxtype* getNodesToComponentMap(Matrix*, int*, wgttype);
idxtype* compSizeDistribution(GraphType*, int*);
int isGraphConnected(GraphType*);
void WriteRMap(const char*, idxtype*, int);
void printHistogram(idxtype*, int, FILE*);
int readClustering(const char *, int *, int);
void ReadGraph(GraphType *, const char *, int *, int, int);
//void ReadTxtGraph(GraphType *, char *, int *, int);
void WritePartition(const char *, idxtype *, int, int);
void my_WritePartition(const char *, idxtype *, int, float);
void my_WritePartitionAddOne(const char *, idxtype *, int);
void my_WriteMappedPartition(const char *, idxtype *, idxtype *, int);
void WriteMeshPartition(const char *, int, int, idxtype *, int, idxtype *);
void WritePermutation(const char *, idxtype *, int);
int CheckGraph(GraphType *);
idxtype *ReadMesh(const char *, int *, int *, int *);
void WriteGraph(const char *, int, idxtype *, idxtype *);
void WriteTxtGraph(const char *, int, idxtype *, idxtype *);
void WriteMatrix(const char *, int, idxtype*, idxtype*, wgttype*);
void WriteGraphWithWts(const char *, int, idxtype*, idxtype*,
idxtype*);
void WriteMappedTxtGraphWithWts(const char *, int,
		idxtype*,idxtype*,idxtype*,idxtype*, int);


/* heteroSparsify.cpp */
//TODO: Define the methods that will be implemented in this file



