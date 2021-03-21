;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-beginner-reader.ss" "lang")((modname |Assignment 0|) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
; Author: Kush Shah


;Purpose: Triple the value of the input
;Signature: Number -> Number
(define (triple n)
  (* 3 n))

;Test-cases for triple
(check-expect (triple 3) 9)
(check-expect (triple -4.2) (* -4.2 3))



;Purpose: Produce the length of a right triangles hypotenuse given the base and height
;Signature: Number Number -> Number

(define (pythag a b)
 (sqrt (+(sqr a) (sqr b))))

;Test-cases for pythag
(check-expect (pythag 3 4) 5)
(check-expect (pythag 5 12) 13)
(check-expect (pythag 3 4) (sqrt (+ (* 3 3) (* 4 4))))



;Purpose: Test if a number is even
;Signature: Number -> Boolean
(define (checkEven? n)
  ;not sure if we needed the corner case
  (if ( = n 0)
      "it's zero!"
      (if (integer? (/ n 2))
          "it's even!"
          "it's odd!")))
  
;Test-cases for checkEven?
(check-expect (checkEven? 5) "it's odd!")
(check-expect (checkEven? 6) "it's even!")
(check-expect (checkEven? 10) "it's even!")
(check-expect (checkEven? 13) "it's odd!")
(check-expect (checkEven? 0) "it's zero!")



;Purpose: Change the string from upper case to lower case depending on the length
;Signature: string -> string

(define (weird-case str)
  (if (integer? (/ (string-length str) 2))
      (string-upcase str)
      (string-downcase str)))



;Test-cases for weird-case

(check-expect (weird-case "kush") "KUSH")
(check-expect (weird-case "WPI") "wpi")
(check-expect (weird-case "abC") "abc")
(check-expect (weird-case "abCd") "ABCD")



;Purpose: Output the shorter string with the larger string appended 
;Signature: String String -> String

(define (sort-strings str1 str2)
  (cond [(> (string-length str1) (string-length str2))
         (string-append str2 str1)]
        [(< (string-length str1) (string-length str2))
         (string-append str1 str2)]
        [(= (string-length str1) (string-length str2))
         "?"]))

;Test-cases for sort-strings
(check-expect (sort-strings "kush" "shah") "?")
(check-expect (sort-strings "door" "frame") "doorframe")
(check-expect (sort-strings "race" "car") "carrace")
(check-expect (sort-strings "abc" "def") "?")
(check-expect (sort-strings "abc" "d") "dabc")
(check-expect (sort-strings "abc" "deff") "abcdeff")