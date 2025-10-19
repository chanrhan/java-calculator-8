package calculator;

// 문자열 덧셈 계산기 클래스
public class StringAdditionCalculator {
    // 정규식 메타 문자들의 ASCII 코드
    private final int[] REGEX_META_CHARSET = new int[]{
            36, 39, 40, 41, 42, 43, 46, 63, 91, 92, 93, 94, 123, 124, 125
    };

    /**
     * 문자열을 분할하는 함수
     * 커스텀 구분자 패턴이 있다면, 커스텀 구분자도 분할 기준에 포함시킨다.
     * 문자열 내에 커스텀 구분자 패턴이 존재하고, 문자열 길이가 커스텀 구분자 패턴의 길이(=5)와 같다면, null 을 반환한다.
     **/
    private String[] split(String input) throws IllegalArgumentException {
        StringBuilder regexStr = new StringBuilder("[,:"); // 기본 구분자

        // 문자열의 길이가 5 이상이고, 커스텀 구분자 패턴이 존재할 경우, 커스텀 구분자를 인식
        if (input.length() >= 5 && input.matches("^/{2}.\\\\n.*")) {
            // 문자열의 길이가 5라면, 커스텀 구분자 패턴 뒤에 수식(연산자 및 피연산자)이 없으므로 0을 반환
            if (input.length() == 5) {
                return null;
            }

            char customSeparator = input.charAt(2); // 커스텀 구분자 추출

            // 커스텀 문자가 숫자 또는 공백, 제어 문자일 경우 예외 발생
            if ((customSeparator <= 32 || customSeparator == 127)
                    || (customSeparator >= 48 && customSeparator <= 57)) {
                throw new IllegalArgumentException("커스텀 구분자에는 숫자 또는 공백,제어 문자가 올 수 없습니다! : " + customSeparator);
            }

            // 커스텀 문자가 정규식의 메타 문자인지 판별
            for (int meta : REGEX_META_CHARSET) {
                if (customSeparator == meta) {
                    regexStr.append("\\"); // 메타 문자라면, 이스케이프 문자 추가
                    break;
                }
            }

            regexStr.append(customSeparator);
            input = input.substring(5); // 커스텀 구분자 패턴 제거
        }

        regexStr.append("]");

        return input.split(regexStr.toString(), -1); // 문자열 분할
    }

    // 입력값을 바탕으로 연산을 수행하는 함수
    public int calculate(String input) throws IllegalArgumentException {
        // 빈 문자열이라면, 0을 반환
        if (input == null || input.isEmpty()) {
            return 0;
        }

        String[] items = split(input);

        // 커스텀 구분자 패턴을 제외하고 남은 문자열이 없다면, 수식이 없으므로 0을 반환
        if (items == null) {
            return 0;
        }

        // 문자열이 연산자로 구분되었지만, 값이 없는 경우, 피연산자가 없으므로 예외 발생
        if (items.length == 0) {
            throw new IllegalArgumentException("피연산자가 존재하지 않습니다!");
        }

        try {
            int sum = 0; // 합계
            int num;

            // 분할된 문자열의 요소들을 순회
            for (String item : items) {
                num = Integer.parseInt(item);
                if (num <= 0) { // 파싱된 정수가 0 또는 음수라면, 예외 발생
                    throw new IllegalArgumentException("피연산자는 양수여야 합니다! : " + num);
                }
                sum += num;
            }

            if (sum < 0) {
                throw new IllegalArgumentException("숫자 합이 정수의 최댓값을 초과했습니다! : " + sum);
            }

            return sum;
        } catch (NumberFormatException e) { // Integer.parseInt() 메서드에서 문자열을 정수로 변환하지 못했을 경우 예외 발생
            throw new IllegalArgumentException("피연산자가 숫자 형식이 아닙니다!");
        }
    }
}
