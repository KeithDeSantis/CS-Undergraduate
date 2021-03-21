#lang racket
;; needed for check-expects
(require test-engine/racket-tests)
;; #:transparent makes structures display a bit nicer
;; #:mutable creates set! methods for a structure -- not automatic
;;      in full racket language

(define-struct graph (name nodes) #:transparent #:mutable)
;a graph is a (make-graph symbol (listof symbol))

(define-struct node (name edges) #:transparent #:mutable)
;a node is a (make-graph symbol (list of symbol))

;; *******************************************************
;; you don't need to understand this part
;; just be sure you use my-eval instead of eval in your code
(define-namespace-anchor a)
(define ns (namespace-anchor->namespace a))


(define-syntax my-eval
  (syntax-rules ()
    [(my-eval arg)
     (eval arg ns)]))
;; *******************************************************

;; DO NOT MODIFY ANYTHING ABOVE THIS LINE
;; -------------------------------------------------------

;Part 1

;defines a variable that can be mutated later
(define-syntax create
  (syntax-rules ()
    [(create name val)
     (begin (define name val) (set! name val))]))

;either create a new graph or a new vertex in a specified graph
(define-syntax new
  (syntax-rules ()
    [(new graph name)
     (create name (make-graph 'name '()))]
    
    [(new vertex n0 in g0)
     (begin (create n0 (make-node 'n0 '()))
            (add-unique 'n0 g0))]))


;Part 2

;create an edge between two nodes
;node node -> (void) or node if edge already exists 
(define-syntax edge
  (syntax-rules ()
    [(edge n0 n1)
     (add-unique 'n1 n0)]))
     
;Establish multiple edges between X amount of nodes
;arbritrary number of nodes seperated by symbols -> (void)
(define-syntax edges
  (syntax-rules (-> <->)
    [(edges n0 -> n1 rest ...)
     (begin (edge n0 n1) (edges n1 rest ...))]
    
    [(edges n0 <-> n1 rest ...)
     (begin (edge n0 n1)
            (edge n1 n0)
            (edges n1 rest ...))]
    
    [(edges n1)
     (void)]))


;Part 3/4

(check-expect (does n1 have a bidirectional edge to n2 ?) #t)
(check-expect (does n0 have a bidirectional edge to n1 ?) #f)
(check-expect (does n1 have an edge to n0 ?) #f)
(check-expect (does n2 have an edge to n0 ?) #t)
(check-expect (does n9 have a path to n0 ?) #t)
(check-expect (does n1 have a path to n9 ?) #f)


;node node -> Boolean
(define-syntax does
  (syntax-rules (have a an to ?)
    [(does n0 have an edge to n1 ?)
     (not (false? (member 'n1 (node-edges n0))))]
    
    [(does n0 have a bidirectional edge to n1 ?)
     (and
      (not (false? (member 'n1 (node-edges n0))))
      (not (false? (member 'n0 (node-edges n1)))))]
    
    [(does n0 have a path to n1 ?)
     (path? n0 n1)]))


; X Structure -> (mutated) Structure
(define (add-unique i s)
  (cond [(graph? s)
         (if (member i (graph-nodes s))
             s
             (set-graph-nodes! s
                               (cons i (graph-nodes s))))]
        [(node? s)
         (if (member i (node-edges s))
             s
             (set-node-edges! s (cons i (node-edges s))))]))


;Part 4

(check-expect (path? n2 n1) #t)
(check-expect (path? n0 n2) #t)
(check-expect (path? n1 n9) #f)

;Determine if there is a path from one node to another 
;node node -> Boolean
(define (path? start-node end-node)
  (local
    [(define (fn-for-node n todo visited)
       (if (or (does n have an edge to end-node ?)
               (equal? (node-name n) (node-name end-node)))
           true
           (if (member (node-name n) visited)
               (fn-for-lon todo visited)
               (fn-for-lon (append (node-edges n) todo)
                           (cons (node-name n) visited)))))
     
     (define (fn-for-lon todo visited)
       (cond
         [(empty? todo) false]
         [else (fn-for-node (my-eval (first todo))
                            (rest todo) visited)]))]
    
    (fn-for-node start-node empty empty)))



(check-expect (all-nodes g0) '(n0 n2 nA n1 n9))
(check-expect (all-nodes g1) '(nA))

;given from Assignment document
(check-expect (all-nodes gA) '(v1 v3 v0 v2))

;Get a list of all reachable nodes of a graph
;graph -> (listofnodes)
(define (all-nodes graph)
  (local
    [(define (fn-for-node n todo visited)
       (if (member (node-name (my-eval n)) visited)
           (fn-for-lon todo visited)
           (fn-for-lon (append
                        (node-edges (my-eval n)) todo)
                       (cons
                        (node-name (my-eval n)) visited))))
     
     (define (fn-for-lon todo visited)
       (cond
         [(empty? todo) visited]
         [else
          (fn-for-node (first todo) (rest todo) visited)]))]
    (fn-for-node (first (graph-nodes graph)) empty empty)))



;for testing
(begin(new graph g0)
      (new graph g1)
      (new graph gT)
      (new vertex n0 in g0)
      (new vertex n1 in g0)
      (new vertex n2 in g0)
      (new vertex n9 in g0)
      (new vertex nA in g1)
      (new vertex nT1 in gT)
      (new vertex nT2 in gT)
      (edges n9 -> n1 <-> n2 -> n0 -> n1 -> nA)
      (edges nT2 <-> nT1 -> nA)
      
;given from Assignment document
      (new graph gA)
      (new graph gB)
      (new vertex v0 in gA)
      (new vertex v1 in gA)
      (new vertex v2 in gA)
      (new vertex v3 in gB)
      (edges v0 -> v1 <-> v2 -> v0 -> v3)
      )
 
(test)