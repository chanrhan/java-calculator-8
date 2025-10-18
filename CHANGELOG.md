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
- 입력받은 문자열을 정규식(`[,:]`)으로 잘라 `String[]` 형태의 피연산자 배열 생성
- 피연산자 배열을 순회하여 `String`요소를 정수로 변환(`parseInt`) 
  - 문자열을 정수로 변환하지 못한 경우는 `NumberFormatException` 예외 메세지를 `IllegalArgumentException` 에 담아 예외를 발생
- 변환된 정수는 결과값에 더하고, 함수에서는 최종 결과값을 반환