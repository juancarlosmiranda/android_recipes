// Generated by view binder compiler. Do not edit!
package com.example.basic_views_03.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.basic_views_03.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentThirdBinding implements ViewBinding {
  @NonNull
  private final NestedScrollView rootView;

  @NonNull
  public final Button buttonThirdNext;

  @NonNull
  public final Button buttonThirdPrevious;

  @NonNull
  public final TextView textviewSecond;

  private FragmentThirdBinding(@NonNull NestedScrollView rootView, @NonNull Button buttonThirdNext,
      @NonNull Button buttonThirdPrevious, @NonNull TextView textviewSecond) {
    this.rootView = rootView;
    this.buttonThirdNext = buttonThirdNext;
    this.buttonThirdPrevious = buttonThirdPrevious;
    this.textviewSecond = textviewSecond;
  }

  @Override
  @NonNull
  public NestedScrollView getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentThirdBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentThirdBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_third, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentThirdBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.button_third_next;
      Button buttonThirdNext = ViewBindings.findChildViewById(rootView, id);
      if (buttonThirdNext == null) {
        break missingId;
      }

      id = R.id.button_third_previous;
      Button buttonThirdPrevious = ViewBindings.findChildViewById(rootView, id);
      if (buttonThirdPrevious == null) {
        break missingId;
      }

      id = R.id.textview_second;
      TextView textviewSecond = ViewBindings.findChildViewById(rootView, id);
      if (textviewSecond == null) {
        break missingId;
      }

      return new FragmentThirdBinding((NestedScrollView) rootView, buttonThirdNext,
          buttonThirdPrevious, textviewSecond);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
