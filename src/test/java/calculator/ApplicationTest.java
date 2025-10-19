package calculator;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {
    // 기본 구분자
    @Test
    void test(){
        assertSimpleTest(()->{
            // 빈 문자열(=빈 줄)일 경우
            run("\n");
            assertThat(output()).contains("결과 : 0");
        });
    }

    @Test
    void exceptionTest() {
        assertSimpleTest(() -> {
            // 문자열 내에 양수가 아닌 값이 있는 경우
            assertThatThrownBy(() -> runException("-1,2,3"))
                    .isInstanceOf(IllegalArgumentException.class);
            assertThatThrownBy(() -> runException("1,0,3"))
                    .isInstanceOf(IllegalArgumentException.class);
            assertThatThrownBy(() -> runException("0"))
                    .isInstanceOf(IllegalArgumentException.class);

            // 피연산자가 숫자가 아닌 경우
            assertThatThrownBy(() -> runException(" "))
                    .isInstanceOf(IllegalArgumentException.class);
            assertThatThrownBy(() -> runException("1,p,3"))
                    .isInstanceOf(IllegalArgumentException.class);
            assertThatThrownBy(() -> runException("1,"))
                    .isInstanceOf(IllegalArgumentException.class);

            // 피연산자가 없는 경우
            assertThatThrownBy(() -> runException(",;"))
                    .isInstanceOf(IllegalArgumentException.class);

            // 문자열 내 정수가 정수 최댓값을 초과할 경우
            assertThatThrownBy(() -> runException("1,"+Integer.MAX_VALUE))
                    .isInstanceOf(IllegalArgumentException.class);
        });
    }

    // 커스텀 구분자
    @Test
    void customSeparatorTest() {
        assertSimpleTest(() -> {
            run("//;\\n");
            assertThat(output()).contains("결과 : 0");
            run("//;\\n1");
            assertThat(output()).contains("결과 : 1");
            run("//;\\n1,2;3;4");
            assertThat(output()).contains("결과 : 10");
            run("//n\\n1,2n3:4");
            assertThat(output()).contains("결과 : 10");

            // 커스텀 구분자에 정규식의 메타 문자가 올 경우
            run("//.\\n1,2.3");
            assertThat(output()).contains("결과 : 6");
            run("//^\\n1,2^3");
            assertThat(output()).contains("결과 : 6");
            run("//$\\n1,2$3");
            assertThat(output()).contains("결과 : 6");
            run("//*\\n1,2*3");
            assertThat(output()).contains("결과 : 6");
            run("//+\\n1,2+3");
            assertThat(output()).contains("결과 : 6");
            run("//?\\n1,2?3");
            assertThat(output()).contains("결과 : 6");
            run("//{\\n1,2{3");
            assertThat(output()).contains("결과 : 6");
            run("//}\\n1,2}3");
            assertThat(output()).contains("결과 : 6");
            run("//[\\n1,2[3");
            assertThat(output()).contains("결과 : 6");
            run("//]\\n1,2]3");
            assertThat(output()).contains("결과 : 6");
            run("//\\\\n1,2\\3");
            assertThat(output()).contains("결과 : 6");
            run("//|\\n1,2|3");
            assertThat(output()).contains("결과 : 6");
            run("//(\\n1,2(3");
            assertThat(output()).contains("결과 : 6");
            run("//)\\n1,2)3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void customSeparatorExceptionTest(){
        assertSimpleTest(()->{
            // 커스텀 구분자 설정 패턴 문자열(// \n)이 잘못되었을 경우
            assertThatThrownBy(()-> runException("//"))
                    .isInstanceOf(IllegalArgumentException.class);
            assertThatThrownBy(()-> runException("/"))
                    .isInstanceOf(IllegalArgumentException.class);
            assertThatThrownBy(()-> runException("/\\"))
                    .isInstanceOf(IllegalArgumentException.class);
            assertThatThrownBy(()-> runException("/\\n"))
                    .isInstanceOf(IllegalArgumentException.class);
            assertThatThrownBy(()-> runException("\\n"))
                    .isInstanceOf(IllegalArgumentException.class);
            assertThatThrownBy(()-> runException("//\\"))
                    .isInstanceOf(IllegalArgumentException.class);

            // 커스텀 구분자 설정 패턴 안에 커스텀 구분자로 쓰일 문자가 없는 경우
            assertThatThrownBy(()-> runException("//\\n"))
                    .isInstanceOf(IllegalArgumentException.class);


            // 커스텀 구분자가 문자가 아닌 경우
            assertThatThrownBy(()-> runException("//2\\n123"))
                    .isInstanceOf(IllegalArgumentException.class);
            assertThatThrownBy(()-> runException("// \\n1,2 3"))
                    .isInstanceOf(IllegalArgumentException.class);

            // 커스텀 구분자가 2개 이상의 문자로 이루어진 문자열일 경우
            assertThatThrownBy(()-> runException("//*@\\n"))
                    .isInstanceOf(IllegalArgumentException.class);
        });
    }



    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
