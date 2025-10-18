- build: upgrade Gradle versions
- feat: setup project

## 2025-10-18
### docs(readme): add README docs

구현할 기능에 대한 명세와 절차를 정리하여 README.md 파일에 작성함

### feat(Application): add default I/O and exception handling

- `Console.readline()` 을 활용한 입력 기능 구현
- `calculate()` 메서드 생성하고 예외 시 예외 출력 후 프로그램 종료하도록 함 

### feat(Application): implement calculate() to sum numbers in a string

문자열 내 숫자를 더해 결과를 반환하는 함수 구현 
- 입력받은 문자열을 기본 구분자를 식별하는 정규표현식(`[,:]`)으로 분할하여 `String[]` 형태의 피연산자 배열 생성
- 피연산자 배열을 순회하여 `String`요소를 정수로 변환(`parseInt`) 
  - 문자열을 정수로 변환하지 못한 경우는 `NumberFormatException` 예외 메세지를 `IllegalArgumentException` 에 담아 예외를 발생
- 변환된 정수는 결과값에 더하고, 함수에서는 최종 결과값을 반환

### feat(Application): add support for identifying custom separator and splitting with it

change #1. 문자열 내에서 커스텀 구분자를 판별하고, 기본 구분자에 커스텀 구분자를 포함하여 문자열을 자르는 기능 추가
- 입력받은 문자열의 길이가 5 이상이고, 커스텀 구분자를 식별하는 정규표현식(`^/{2}.\\n.*`)의 패턴과 일치하는지 확인 
  - 일치하다면, 문자열을 분할할 때 사용하는 정규표현식에 문자열의 3번째 문자(커스텀 구분자)를 삽입.
    그리고 피연산자와 구분자만 남도록 문자열의 앞 5자리를 자름
- 이후 동일하게 연산 과정을 수행

### fix(Application): correct output format and exception handling

결과값 출력 방식 변경
- 기존: "6"
- 변경: "결과 : 6"
예외 처리 방식 변경
- 기존: `calculate()`에서 예외 발생 시, `main()`에서 이를 catch 한 후 터미널 콘솔에 오류메세지 출력
- 변경: `calculate()`에서 예외 발생 시 `main()` catch 하지 않고 그대로 전파

### fix(Application): validate positive numbers in a string 

예외 추가
- 분할된 문자열에서, 파싱(parsing)된 정수가 양수가 아니라면, 예외 발생 

### test(ApplicationTest): add test code 

- 테스트 코드 추가 
- 문자열 내 숫자의 합이 정수의 최댓값을 넘어갈 때, 예외 발생 