;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-beginner-reader.ss" "lang")((modname desantis-iyengar-hw1) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #t)))
;; HW1
;; Keith DeSantis kwdesantis
;; Partner: Maanav Iyengar miyengar

;;
;; 1
;;

(define-struct vehicle (make model year-of-manufacture odometer passengers date-of-exp))  ; define struct for a vehicle, calls on the struc "date"
;; a Vehicle is a (make-vehicle String String Natural Natural Natural Date)
;; interp: represents a certain car in the car rental agency's inventory
;; make is the car's brand
;; model is the car's model
;; year-of-manufacture is the car's manufacturing year
;; odometer is the number of miles read on the car's odometer
;; passengers is the number of passengers the car can hold
;; date-of-exp is a structure that is defined below, it represents the date that the car's registration will expire

(define-struct date (year month day)) ; define struct for a date
;; a Date is a (make-date Natural Natural Natural)
;; interp: represents the date given as a year, month, and date
;; year is the year of the date
;; month is the month of the date
;; day is the day of the date

(define DATE1 (make-date 2021 1 1)) ; making some example dates
(define DATE2 (make-date 2024 2 1))
(define DATE3 (make-date 2021 12 28))

(define CAR1 (make-vehicle "Chevy" "Tahoe" 2007 90000 5 DATE1)) ; making some example cars
(define CAR2 (make-vehicle "Toyota" "Tacoma" 2014 6000 2 DATE2))
(define CAR3 (make-vehicle "Nissan" "Rogue" 2011 140000 5 DATE3))
(define CAR4 (make-vehicle "Nissan" "Rogue" 2007 140000 5 DATE3))



;;
;; 2
;;

;; Signature for make-vehicle
;; make-vehicle: String String Natural Natural Natural Date -> Vehicle

;; Signature for vehicle-make
;; vehicle-make: Vehicle -> String

;; Signature for vehicle-model
;; vehicle-model: Vehicle -> String

;; Signature for vehicle-year-of-manufacture
;; vehicle-year-of-manufacture: Vehicle -> Natural

;; Signature for vehicle-odometer
;; vehicle-odometer: Vehicle -> Natural

;; Signature for vehicle-passengers
;; vehicle-passengers: Vehicle -> Natural

;; Signature for vehicle-date-of-exp
;; vehicle-date-of-exp: Vehicle -> Date




;;
;; 3
;;

;; low-mileage-toyota?: vehicle Natural -> Boolean
;; consumes a Vehicle and a number of miles (Natural) and produces a Boolean that is true if the vehicle is a Toyota with a odometer reading of less than or equal to the Natural provided

(check-expect (low-mileage-toyota? CAR1 100000000) false) ; case where car isn't a Toyota and has low mileage
(check-expect (low-mileage-toyota? CAR2 10000) true) ; case where car is a Toyota and has low mileage
(check-expect (low-mileage-toyota? CAR2 10) false) ; case where car is Toyota but has high mileage
(check-expect (low-mileage-toyota? CAR1 10) false) ; case where car isn't a Toyota and has high mileage

;; (define (low-mileage-toyota? car miles) true)  ; stub

(define (low-mileage-toyota? car miles)
  (and (string=? "Toyota" (vehicle-make car)) (< (vehicle-odometer car) miles))) ; function definition, uses an and statement to confirm that the car is a Toyota AND has less than wanted miles




;;
;; 4
;;

;; newer-car: Vehicle Vehicle -> Vehicle
;; consumes two Vehicles and produces the Vehicle with the more recent year of manufacturing

(check-expect (newer-car CAR1 CAR2) CAR2) ; case where one car is newer than the other
(check-expect (newer-car CAR1 CAR4) CAR1) ; case where both cars have same manufacturing year

; (define (newer-car carone cartwo) carone)  ;stub

(define (newer-car carone cartwo) 
  (if (>= (vehicle-year-of-manufacture carone) (vehicle-year-of-manufacture cartwo))   ; newer-car definition
      carone 
      cartwo))



;;
;; 5
;;

;; update-odometer: Vehicle Natural -> Vehicle
;; consumes a Vehicle and a Natural number of miles then produces a "new version" of that Vehicle where the odometer is updated to have that amount of miles added

(check-expect (update-odometer CAR1 10) (make-vehicle "Chevy" "Tahoe" 2007 90010 5 DATE1)) ; case where miles are added
(check-expect (update-odometer CAR2 0) (make-vehicle "Toyota" "Tacoma" 2014 6000 2 DATE2)) ; case where no miles are added

; (define (update-odometer usedcar newmiles) updatedcar) ; stub

(define (update-odometer usedcar newmiles) ; update-odometer definition
  (make-vehicle (vehicle-make usedcar) (vehicle-model usedcar) (vehicle-year-of-manufacture usedcar) (+ (vehicle-odometer usedcar) newmiles) (vehicle-passengers usedcar) (vehicle-date-of-exp usedcar)))




;;
;; 6
;;

; Creating a Helper Function:

(define OLDDATE (make-date 2000 1 28)) ; defining some example dates
(define NEWDATE (make-date 2040 1 15))
(define SAMEDATE (make-date 2021 1 1))
(define SAMEMONTHAFTER (make-date 2021 1 2))
(define SAMEYEARAFTER (make-date 2021 2 1))
(define SAMEMONTHBEFORE (make-date 2021 12 25))
(define SAMEYEARBEFORE (make-date 2021 11 2))


;; date-compare: Date Date -> Boolean
;; consumes two Dates and produces a Boolean that is true if the first date comes after the second or if they are the same date

(check-expect (date-compare OLDDATE (vehicle-date-of-exp CAR1)) false) ; case where first date is before second
(check-expect (date-compare NEWDATE (vehicle-date-of-exp CAR1)) true) ; case where first date is after second
(check-expect (date-compare SAMEDATE (vehicle-date-of-exp CAR1)) true) ; case where two dates are the same
(check-expect (date-compare SAMEMONTHAFTER (vehicle-date-of-exp CAR1)) true) ; case where the days are within the same month and it is expired
(check-expect (date-compare SAMEYEARAFTER (vehicle-date-of-exp CAR1)) true) ; case where the days are within the same year and it is expired
(check-expect (date-compare SAMEYEARBEFORE (vehicle-date-of-exp CAR3)) false) ; case where the days are within the same year and it isn't expired
(check-expect (date-compare SAMEMONTHBEFORE (vehicle-date-of-exp CAR3)) false) ; case where the days are within the same month and it isn't expired

;; (define (date-compare exp-date second-date) true) ; stub

(define (date-compare exp-date second-date)
  (if (> (date-year exp-date) (date-year second-date)) ; defining helper function, which compares years,
     true
     (if (< (date-year exp-date) (date-year second-date))
         false
         (if (> (date-month exp-date) (date-month second-date))   ;  months,
            true
            (if (< (date-month exp-date) (date-month second-date))
              false
              (if (> (date-day exp-date) (date-day second-date))  ; and dates through a series of ordered if statements
                true
                (if (< (date-day exp-date) (date-day second-date)) 
                 false
                 true)))))))  ; if they are the same date it defaults to true
              
; Creating registration-valid?

;; registration-valid?: Vehicle Date -> Boolean
;; consumes a Vehicle and a Date and produces a Boolean that is true if the vehicle's date of expiration comes after or is the same as that date

(check-expect (registration-valid? CAR1 OLDDATE) true) ; case where date of expiration is years after second
(check-expect (registration-valid? CAR1 NEWDATE) false) ; case where date of expiration is years before second
(check-expect (registration-valid? CAR1 SAMEDATE) true) ; case where two dates are the same
(check-expect (registration-valid? CAR1 SAMEMONTHAFTER) false) ; case where the days are within the same month and it is expired
(check-expect (registration-valid? CAR1 SAMEYEARAFTER) false) ; case where the days are within the same year and it is expired
(check-expect (registration-valid? CAR3 SAMEYEARBEFORE) true) ; case where the days are within the same year and it isn't expired
(check-expect (registration-valid? CAR3 SAMEMONTHBEFORE) true) ; case where the days are within the same month and it isn't expired

 
;; (define (registration-valid? car-to-check day) true) ; stub

(define (registration-valid? car-to-check day)
  (date-compare (vehicle-date-of-exp car-to-check) day)) ; defining registration-valid? using helper function, date-compare


      