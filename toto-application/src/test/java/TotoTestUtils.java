import com.example.training.toto.domain.OutcomeSet;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.example.training.toto.domain.Outcome.fromValue;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TotoTestUtils {

    public static OutcomeSet toOutcomeSet(String o1, String o2, String o3, String o4, String o5, String o6, String o7,
                                          String o8, String o9, String o10, String o11, String o12, String o13, String o14) {
        return OutcomeSet.builder()
                .o1(fromValue(o1))
                .o2(fromValue(o2))
                .o3(fromValue(o3))
                .o4(fromValue(o4))
                .o5(fromValue(o5))
                .o6(fromValue(o6))
                .o7(fromValue(o7))
                .o8(fromValue(o8))
                .o9(fromValue(o9))
                .o10(fromValue(o10))
                .o11(fromValue(o11))
                .o12(fromValue(o12))
                .o13(fromValue(o13))
                .o14(fromValue(o14))
                .build();
    }
}
