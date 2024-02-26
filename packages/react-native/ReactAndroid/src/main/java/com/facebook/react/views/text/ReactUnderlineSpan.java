/*
 * Copyright (c) Meta Platforms, Inc. and affiliates.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.facebook.react.views.text;

import android.os.Build;
import android.text.TextPaint;
import android.text.style.UnderlineSpan;
import androidx.annotation.Nullable;

import java.lang.reflect.Method;

/*
 * Wraps {@link UnderlineSpan} as a {@link ReactSpan}.
 */
public class ReactUnderlineSpan extends UnderlineSpan implements ReactSpan {
  private final @Nullable Integer mUnderlineColor;

  public ReactUnderlineSpan(@Nullable Integer color) {
    mUnderlineColor = color;
  }

  @Override
  public void updateDrawState(TextPaint textPaint) {
    if (mUnderlineColor == null) {
      super.updateDrawState(textPaint);
    } else {
      float mTaskadeUnderlineThickness = 8.0f;

      /**
       * Enables underline color support for TextPaint. The method is publicly
       * available starting from API 29
       * and was hidden before. We will have to resort to reflection techniques to
       * ensure we can use this API.
       * The API is defined here (as of this diff):
       * https://android.googlesource.com/platform/frameworks/base/+/refs/heads/android13-gsi/core/java/android/text/TextPaint.java
       */
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        textPaint.underlineColor = mUnderlineColor;
        textPaint.underlineThickness = mTaskadeUnderlineThickness;
      } else {
        try {
          final Method method = TextPaint.class.getMethod("setUnderlineText", Integer.TYPE, Float.TYPE);
          method.invoke(textPaint, mUnderlineColor, mTaskadeUnderlineThickness);
        } catch (final Exception e) {
          // Render default underline
          textPaint.setUnderlineText(true);
        }
      }
    }
  }

  public int getUnderlineColor() {
    return mUnderlineColor;
  }
}
