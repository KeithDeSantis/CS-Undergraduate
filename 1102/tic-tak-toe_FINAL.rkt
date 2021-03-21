;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname tic-tak-toe_FINAL) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
(require 2htdp/image)
(require 2htdp/universe)

(define-struct board (img diff turn state))
;; Board is (make-board image Natural boolean (listof String))
;; IMG is the image of the BOARD
;; turn d

;CONSTANTS

(define SIZE 400)
;(define LINE-WIDTH 50)



(define top-buffer  (* SIZE .1))
(define bottom-buffer (- SIZE top-buffer))
(define one-third  (/ ( - SIZE (* top-buffer 2)) 3))
(define two-thirds (* one-third 2))


(define LINE-LENGTH (- SIZE (* top-buffer 2)))
(define LINE (rectangle 5 LINE-LENGTH "solid" "Dark Green"))


(define HALF-GRID (overlay/offset LINE one-third 0 LINE))
(define BOARD-IMG (overlay HALF-GRID
                           (rotate 90 HALF-GRID)))
(define MTS (empty-scene SIZE SIZE))


(define START (make-board BOARD-IMG 0 false (list "N" "N" "N"
                                                  "N" "N" "N"
                                                  "N" "N" "N")))

(define TEST1 (make-board BOARD-IMG 0 false (list "N" "N" "N"
                                                  "N" "N" "N"
                                                  "N" "N" "N")))

(define WIN1 (make-board BOARD-IMG 0 false (list "X" "O" "N"
                                                 "N" "X" "N"
                                                 "O" "N" "X")))

(define DRAW1 (make-board BOARD-IMG 0 false (list "X" "O" "O"
                                                  "O" "X" "X"
                                                  "X" "X" "O")))

(define ALMOST-WIN (make-board BOARD-IMG 2 false (list "X" "O" "N"
                                                 "N" "X" "N"
                                                 "O" "N" "N")))




(define FONT-SIZE (round(/ SIZE 5)))






(define (main b)
  (big-bang b
    (on-tick tock)
    (on-draw render)
    (on-mouse click)
    (on-key diff)))



;Board -> Board
(define (tock b)
  (local[(define (c-turn b)
  (cond[(= (board-diff b) 0) (update b (random 9) "O")]
       [(= (board-diff b) 1) (update b (winner-move b "O") "O")]
       [(= (board-diff b) 2)  (update b (winner-move b "X") "O") ]))]
  (cond [(or (winner b) (tie? b))
         b]
        [(board-turn b)
         (c-turn b)]
        [else b])))



;Board -> Image
(define (render b)
  (local [(define (turn? b)
            (if b
                "O"
                "X"))]
    (place-image  (cond [(winner b)
                         (place-image (overlay (text (string-append (turn? (not (board-turn b)))
                                                                    "WIN") 100 "green")
                                               (board-img b))
                                      (/ SIZE 2) (/ SIZE 2) MTS)]
                        [(tie? b)
                         (place-image (overlay (text "DRAW" FONT-SIZE "green")
                                               (board-img b))
                                      (/ SIZE 2) (/ SIZE 2) MTS)]
                        [else (board-img b)])
                  (/ SIZE 2) (/ SIZE 2) MTS)))


;Check whose turn it is
;Borad -> String



;Board x-pos y-pos mouse-event -> Board
(define (click b x y e)
  (if(string=? "button-down" e)
     (cond[(false? (board-turn b))
           (update b (get-spot x y) "X")]
          [else b])
     b))

(check-expect (click TEST1 (/ SIZE 2) (/ SIZE 2) "button-down")
              (make-board (place-image
                           (text "X" FONT-SIZE "Light Goldenrod")
                           (- two-thirds (/ one-third 2))
                           (+ one-third (/ one-third 2))
                           (board-img TEST1)) (board-diff TEST1)
                                              true  (list  "N" "N" "N"
                                                           "N" "X" "N"
                                                           "N" "N" "N")))



;Produce 0-8 coresponding to section of board
;Natural Natrual -> Natural
(define (get-spot x y)
  (cond[(and (> x top-buffer) (< x (+ one-third top-buffer)) (> y top-buffer) (< y(+  one-third top-buffer))) 0] ;TL
       [(and (> x one-third) (< x (+ two-thirds top-buffer)) (> y top-buffer) (< y (+ one-third top-buffer))) 1] ;TM
       [(and (> x two-thirds) (< x bottom-buffer) (> y top-buffer) (< y (+ one-third top-buffer))) 2] ;TR
       [(and (> x top-buffer) (< x (+ one-third top-buffer)) (> y (+ one-third top-buffer)) (< y (+ two-thirds top-buffer))) 3] ;ML
       [(and (> x (+ one-third top-buffer)) (< x (+ two-thirds top-buffer)) (> y (+ one-third top-buffer)) (< y (+ two-thirds top-buffer))) 4] ;MM
       [(and (> x (+ two-thirds top-buffer)) (< x bottom-buffer) (> y (+ one-third top-buffer)) (< y (+ two-thirds top-buffer))) 5] ;MR
       [(and (> x top-buffer) (< x (+ one-third top-buffer)) (< y bottom-buffer) (> y (+ two-thirds top-buffer))) 6] ;BL
       [(and (> x one-third) (< x two-thirds) (< y bottom-buffer) (> y two-thirds)) 7] ;BM
       [(and (> x (+ two-thirds top-buffer)) (< x bottom-buffer) (< y bottom-buffer) (> y (+ two-thirds top-buffer))) 8] ;BR 
       [else -1]))

(check-expect (get-spot (/ SIZE 2) (/ SIZE 2))4)
(check-expect (get-spot SIZE SIZE)-1)


;Checks to see if input spot is occupied
;Board Natural -> Boolean
(define (taken? b spot)
  (local[(define (check-spot los current-spot)
           (cond[(empty? los) true]
                [(and (string=? (first los) "N") (= current-spot spot)) false]
                [else (check-spot (rest los) (add1 current-spot))]))]
    (check-spot (board-state b) 0)))

(check-expect (taken? (make-board BOARD-IMG 0 false (list "X" "N" "N"
                                                          "N" "N" "N"
                                                          "N" "N" "N")) 0) true)

(check-expect (taken? (make-board BOARD-IMG 0 false (list  "N" "N" "N"
                                                           "N" "N" "N"
                                                           "N" "N" "N")) 5) false)

;Update the world state for render
; Board Natural String -> Board
(define (update b spot move)

  (local [(define (new-state state current-spot)
            (cond
              [(empty? state) (list 0)]
              [(= spot current-spot) (cons move (rest state))]
              [else (cons (first state) (new-state (rest state) (add1 current-spot)))]))
                  

          (define (change b SPOT move)
            (local [(define (show color)
                      (cond[(= SPOT -1) (board-img b)]
                           [(= SPOT 0)
                            (place-image
                             (text move FONT-SIZE color)
                             (/ one-third 2) 
                             (/ one-third 2)
                             (board-img b))]
                           [(= SPOT 1)
                            (place-image
                             (text move FONT-SIZE color)
                             (- two-thirds (/ one-third 2))
                             (/ one-third 2)
                             (board-img b))]
                           [(= SPOT 2)
                            (place-image
                             (text move FONT-SIZE color)
                             (+ two-thirds (/ one-third 2))
                             (/ one-third 2)
                             (board-img b))]
                           [(= SPOT 3)
                            (place-image
                             (text move FONT-SIZE color)
                             (/ one-third 2) 
                             (+ one-third (/ one-third 2))
                             (board-img b))]
                           [(= SPOT 4)
                            (place-image
                             (text move FONT-SIZE color)
                             (- two-thirds (/ one-third 2))
                             (+ one-third (/ one-third 2))
                             (board-img b))]
                           [(= SPOT 5)
                            (place-image
                             (text move FONT-SIZE color)
                             (+ two-thirds (/ one-third 2))
                             (+ one-third (/ one-third 2))
                             (board-img b))]
                           [(= SPOT 6)
                            (place-image
                             (text move FONT-SIZE color)
                             (/ one-third 2) 
                             (+ two-thirds (/ one-third 2))
                             (board-img b))]
                           [(= SPOT 7)
                            (place-image
                             (text move FONT-SIZE color)
                             (- two-thirds (/ one-third 2))
                             (+ two-thirds (/ one-third 2))
                             (board-img b))]
                           [(= SPOT 8)
                            (place-image
                             (text move FONT-SIZE color)
                             (+ two-thirds (/ one-third 2))
                             (+ two-thirds (/ one-third 2))
                             (board-img b))]))]
              (if (string=? move "X")
                  (show "Light Goldenrod")
                  (show "Light Turquoise"))))]




    (cond [(and (string=? "X" move) (not(taken? b spot)))
           (make-board (change b spot move) (board-diff b) (not (board-turn b)) (new-state (board-state b) 0))]
          
          [(and (string=? "X" move) (taken? b spot)) 
           b]
          
          [(and (string=? "O" move) (not(taken? b spot)))
           (make-board (change b spot move) (board-diff b) (not (board-turn b)) (new-state (board-state b) 0))]
          
          [(and (string=? "O" move) (taken? b spot))
           (update b (random 9) move)])))
                                                                                                                                                                             
(check-expect (update TEST1 4 "O")
              (make-board
               (place-image
                (text "O" FONT-SIZE "Light Turquoise")
                (- two-thirds (/ one-third 2))
                (+ one-third (/ one-third 2))
                (board-img TEST1))
               (board-diff TEST1)
               (not (board-turn TEST1))
               (list "N" "N" "N"
                     "N" "O" "N"
                     "N" "N" "N")))
(check-expect (update TEST1 7 "X") (make-board
                                    (place-image
                                     (text "X" FONT-SIZE "Light Goldenrod")
                                     (- two-thirds (/ one-third 2))
                                     (+ two-thirds (/ one-third 2))
                                     (board-img TEST1))
                                    (board-diff TEST1)
                                    (not (board-turn TEST1))
                                    (list "N" "N" "N"
                                          "N" "N" "N"
                                          "N" "X" "N")
                                    ))



;Checks to see if there is a three in a row/colom/diagonal
;(listofString) can contain only "X" or "O"
;(listofString) -> Boolean
(define (equal-all los)
  (string=? (first los) (second los) (third los)))
(check-expect (equal-all (list "X" "X" "X"))true)
(check-expect (equal-all (list "X" "O" "X")) false)


; (listof String) -> Boolean
(define (winner b)
  (local[(define los (board-state b))
         (define (ninth los)
           (first (rest (rest (rest (rest (rest (rest (rest (rest los))))))))))
         (define win-conds
           (list (list (first los) (second los) (third los))
                 (list (fourth los) (fifth los) (sixth los))
                 (list (seventh los) (eighth los) (ninth los))
                 (list (first los) (fourth los) (seventh los))
                 (list (second los) (fifth los) (eighth los))
                 (list (third los) (sixth los) (ninth los))
                 (list (first los) (fifth los) (ninth los))
                 (list (third los) (fifth los) (seventh los))))
         (define (win los)
           (cond [(empty? los) false]
                 [else
                  (if (empty? (filter (lambda (s) (string=? "N" s)) (first los)))
                      (or (equal-all (first los)) (win (rest los)))
                      (win (rest los)))]))]
    (win win-conds)))

(check-expect (winner WIN1) true)
(check-expect (winner TEST1) false)
(check-expect (winner DRAW1) false)






;Check to see if the board is full resulting in a draw
;Board -> Boolean
(define (tie? b)
  (empty? (filter (lambda (s) (string=? "N" s)) (board-state b))))
(check-expect (tie? TEST1) false)
(check-expect (tie? WIN1) false)
(check-expect (tie? DRAW1) true)



(define (diff b key)
  (if (or (key=? key "0")(key=? key "1")(key=? key "2"))
      (make-board (board-img b)(string->number key)(board-turn b)(board-state b))
      b))
(check-expect (diff TEST1 "2")
              (make-board (board-img TEST1)(string->number "2")(board-turn TEST1)(board-state TEST1)))
(check-expect (diff TEST1 "0")
              (make-board (board-img TEST1)(string->number "0")(board-turn TEST1)(board-state TEST1)))


                
(define (winner-move b move)
  (local[(define (winner-move los cnt)
           (cond [(empty? los) (random 9)]
                 [else
                  (if (and (not (taken? b cnt)) (winner (update b cnt move)))
                      cnt
                      (winner-move (rest los) (add1 cnt)))]))]
    (winner-move (board-state b) 0)))

(check-expect (winner-move ALMOST-WIN "X") 8)


(main START)
