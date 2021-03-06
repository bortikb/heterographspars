
 # MLR-MCL (Multi-Level Regularized Markov Clustering) - Version 1.2
 # 
 # Copyright 2010, The Ohio State University. 
 # Portions Copyright 1997, Regents of the University of Minnesota.
 # All rights reserved.
 # 
 # Redistribution and use in source and binary forms, with or
 # without modification, are
 # permitted provided that the following conditions are met:
 # 
 # 1. Redistributions, with or without modifications, of source 
 # code must retain the above
 # copyright notice, this list of conditions and the following
 # disclaimer.
 # 
 # 2. Redistributions, with or without modifications, in binary form 
 # must reproduce the above
 # copyright notice, this list of conditions and the following
 # disclaimer
 # in the documentation andor other materials provided with the
 # distribution.
 # 
 # 3. The names of the Ohio
 # State University, the University of Minnesota and
 # their contributors may not be used to endorse or promote products
 # derived from this software without specific prior permission. 
 # 
 # THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND
 # CONTRIBUTORS
 # "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 # LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
 # FOR
 # A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
 # COPYRIGHT
 # OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 # INCIDENTAL,
 # SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 # LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 # USE,
 # DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 # ANY
 # THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
 # TORT
 # (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE
 # USE
 # OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH
 # DAMAGE.
 # 
 #

 # Contributors: Venu Satuluri, Srinivasan Parthasarathy
 # 
 # Reference: "Scalable Graph Clustering using Stochastic Flows",
 # KDD 2009. http:doi.acm.org10.11451557019.1557101
 #



def wordcount(fname, needwords=False):
	import re
	rx=re.compile("\\s+")
	f=file(fname,"r")
	nlines=0
	nwords=0
	for line in f:
		nlines += 1
		if needwords and len(line.strip()) > 0:
			nwords += len(rx.split(line.strip()))
	if needwords:
		return (nlines,nwords)
	else:
		return nlines

if __name__ == "__main__":
	import sys
	print wordcount(sys.argv[1],True)
