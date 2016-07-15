## SpannableTextView
 Spannable을 이용한 텍스트뷰 입니다. 텍스트 컬러, bold, italic 등 다양한 효과를 텍스트 뷰에 적용 할 수 있습니다. 지원되는 효과는 다음과 같습니다.
  - text color
  - link text color
  - bold (**BOLD**)
  - italic (*italic*)
  - bold, italic (***BOLD-italic***)
  - under line 
  - strke (~~strike~~)
  - super script 
  - sub script
  - clickable span string  
  
### 1. screen shot
![screen shot image](https://github.com/ksu3101/TIL/blob/master/Android/images/fixed_img.png)
  
### 2. install
 아래의 내용을 app의 `build.gradle`에 추가 해 주세요. 
```
dependencies {
  compile 'kr.swkang.spannabletextview:libspannabletextview:0.0.1'
}
```  
   
### 3. use guide
 [Sample Activity](https://github.com/ksu3101/SpannableTextView/blob/master/app/src/main/java/kr/swkang/sample/SampleMainActivity.java)  
#### 3.1 사용 예
```java
    SpannableTextView stv1 = (SpannableTextView) findViewById(R.id.stv_1);

    SpannableTextView.Span span = new SpannableTextView.Span(stv1, "Hello World!!! ")
        .textColor(Color.GRAY)
        .bold()
        .textSizeSP(26)
        .build();
    stv1.addSpan(span);

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

    SpannableTextView stv2 = (SpannableTextView) findViewById(R.id.stv_2);
    stv2.addSpan(
        new SpannableTextView.Span(stv2, R.string.rtext1)
            .textSizeSP(16)
            .textColor(Color.BLACK)
            .bold()
            .build()
    );
```

### 4. license
```
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 1):
 * <burkdog34@gmail.com> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return KangSung-Woo
 * ----------------------------------------------------------------------------
```
