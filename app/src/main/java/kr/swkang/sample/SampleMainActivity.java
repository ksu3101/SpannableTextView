package kr.swkang.sample;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Toast;

import kr.swkang.spannabletextview.SpannableTextView;

/**
 * @author KangSung-Woo
 * @since 2016/07/15
 */
public class SampleMainActivity
    extends Activity {
  private static final String TAG = SampleMainActivity.class.getSimpleName();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.sample_main_activity);

    SpannableTextView stv1 = (SpannableTextView) findViewById(R.id.stv_1);

    final SpannableTextView.Span span = new SpannableTextView.Span(stv1, "Hello World!!! ")
        .textColor(Color.GRAY)
        .bold()
        .textSizeSP(26)
        .build();
    stv1.addSpan(span);

    stv1.addSpan(
        new SpannableTextView.Span(stv1, "\n반가워요!! ")
            .textColorRes(R.color.colorAccent)
            .italic()
            .textSizePX(getResources().getDimensionPixelSize(R.dimen.textsize_def))
            .build()
    );
    stv1.addSpan(
        new SpannableTextView.Span(stv1, " :)  ")
            .textColor(Color.rgb(100, 100, 100))
            .build()
    );

    stv1.addSpan(
        new SpannableTextView.Span(stv1, "(Click Link)")
            .click(
                new ClickableSpan() {
                  @Override
                  public void onClick(View widget) {
                    Toast.makeText(SampleMainActivity.this, "첫번째 링크를 터치 했습니다.", Toast.LENGTH_SHORT).show();
                  }
                }
            )
            .linkTextColorRes(R.color.colorPrimary)
            .build()
    );

    stv1.addSpan(
        new SpannableTextView.Span(stv1, " // ")
            .build()
    );

    stv1.addSpan(
        new SpannableTextView.Span(stv1, "(Touch this)")
            .click(
                new ClickableSpan() {
                  @Override
                  public void onClick(View widget) {
                    Toast.makeText(SampleMainActivity.this, "두번째 링크를 터치 했습니다.", Toast.LENGTH_SHORT).show();
                  }

                  @Override
                  public void updateDrawState(TextPaint ds) {
                    super.updateDrawState(ds);

                    // hide link under line
                    ds.setUnderlineText(false);
                  }
                }
            )
            .linkTextColorRes(R.color.colorPrimaryDark)
            .build()
    );

    //stv1.clearSpans();

    SpannableTextView stv2 = (SpannableTextView) findViewById(R.id.stv_2);
    stv2.addSpan(
        new SpannableTextView.Span(stv2, R.string.rtext1)
            .textSizeSP(16)
            .textColor(Color.BLACK)
            .bold()
            .build()
    );

    SpannableTextView stv3 = (SpannableTextView) findViewById(R.id.stv_3);
    stv3.addSpan(
        new SpannableTextView.Span(stv3, R.string.rtext2)
            .textSizeSP(12)
            .textColor(Color.GRAY)
            .build()
    );

  }

}
