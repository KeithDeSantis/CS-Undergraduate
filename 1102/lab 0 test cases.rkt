;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-beginner-reader.ss" "lang")((modname |lab 0 test cases|) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
;; the provided test cases give full code coverage and serve as more
;; complete documentation about expected behavior.  typically there
;; will be many fewer test cases provided.

;; also, these test cases are purposefully written to not show how the
;; answers were computed in Racket.  that is poor practice, but necessary
;; as the main goal of the assignment is for you to learn Racket.

;; test cases for Q1
(check-expect (triple 3) 9)
;; you should write another test case here

;; test cases for Q2
(check-expect (pythag 3 4) 5)
;; you should write another test case here

;; test cases for Q3
(check-expect (is-even? 5) "it's odd!")
(check-expect (is-even? 6) "it's even!")
;; you should write another test case here

;; test cases for Q4
(check-expect (weird-case "abC") "abc")
(check-expect (weird-case "abCd") "ABCD")
;; you should write another test case here

;; test cases for Q5
(check-expect (sort-strings "abc" "def") "?")
(check-expect (sort-strings "abc" "d") "dabc")
(check-expect (sort-strings "abc" "deff") "abcdeff")
;; you should write another test case here