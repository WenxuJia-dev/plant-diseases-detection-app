// Generated by view binder compiler. Do not edit!
package com.tencent.yolov5ncnn.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.tencent.yolov5ncnn.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FirstLayoutBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ConstraintLayout ConstraintLayout;

  @NonNull
  public final BottomAppBar appbar;

  @NonNull
  public final CoordinatorLayout coordinatorLayout;

  @NonNull
  public final TextView editTextText;

  @NonNull
  public final ImageView imagebot;

  @NonNull
  public final ImageView imagebot2;

  @NonNull
  public final ImageView imaigebot3;

  @NonNull
  public final ScrollView scrollView2;

  @NonNull
  public final TextView textView10;

  @NonNull
  public final TextView textView11;

  @NonNull
  public final TextView textView12;

  @NonNull
  public final TextView textView20;

  @NonNull
  public final TextView textView21;

  @NonNull
  public final TextView textView5;

  @NonNull
  public final TextView textView6;

  @NonNull
  public final RecyclerView viewCatetory;

  @NonNull
  public final RecyclerView viewOnDetect;

  @NonNull
  public final RecyclerView viewPlantdoc;

  private FirstLayoutBinding(@NonNull ConstraintLayout rootView,
      @NonNull ConstraintLayout ConstraintLayout, @NonNull BottomAppBar appbar,
      @NonNull CoordinatorLayout coordinatorLayout, @NonNull TextView editTextText,
      @NonNull ImageView imagebot, @NonNull ImageView imagebot2, @NonNull ImageView imaigebot3,
      @NonNull ScrollView scrollView2, @NonNull TextView textView10, @NonNull TextView textView11,
      @NonNull TextView textView12, @NonNull TextView textView20, @NonNull TextView textView21,
      @NonNull TextView textView5, @NonNull TextView textView6, @NonNull RecyclerView viewCatetory,
      @NonNull RecyclerView viewOnDetect, @NonNull RecyclerView viewPlantdoc) {
    this.rootView = rootView;
    this.ConstraintLayout = ConstraintLayout;
    this.appbar = appbar;
    this.coordinatorLayout = coordinatorLayout;
    this.editTextText = editTextText;
    this.imagebot = imagebot;
    this.imagebot2 = imagebot2;
    this.imaigebot3 = imaigebot3;
    this.scrollView2 = scrollView2;
    this.textView10 = textView10;
    this.textView11 = textView11;
    this.textView12 = textView12;
    this.textView20 = textView20;
    this.textView21 = textView21;
    this.textView5 = textView5;
    this.textView6 = textView6;
    this.viewCatetory = viewCatetory;
    this.viewOnDetect = viewOnDetect;
    this.viewPlantdoc = viewPlantdoc;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FirstLayoutBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FirstLayoutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.first_layout, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FirstLayoutBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      ConstraintLayout ConstraintLayout = (ConstraintLayout) rootView;

      id = R.id.appbar;
      BottomAppBar appbar = ViewBindings.findChildViewById(rootView, id);
      if (appbar == null) {
        break missingId;
      }

      id = R.id.coordinatorLayout;
      CoordinatorLayout coordinatorLayout = ViewBindings.findChildViewById(rootView, id);
      if (coordinatorLayout == null) {
        break missingId;
      }

      id = R.id.editTextText;
      TextView editTextText = ViewBindings.findChildViewById(rootView, id);
      if (editTextText == null) {
        break missingId;
      }

      id = R.id.imagebot;
      ImageView imagebot = ViewBindings.findChildViewById(rootView, id);
      if (imagebot == null) {
        break missingId;
      }

      id = R.id.imagebot2;
      ImageView imagebot2 = ViewBindings.findChildViewById(rootView, id);
      if (imagebot2 == null) {
        break missingId;
      }

      id = R.id.imaigebot3;
      ImageView imaigebot3 = ViewBindings.findChildViewById(rootView, id);
      if (imaigebot3 == null) {
        break missingId;
      }

      id = R.id.scrollView2;
      ScrollView scrollView2 = ViewBindings.findChildViewById(rootView, id);
      if (scrollView2 == null) {
        break missingId;
      }

      id = R.id.textView10;
      TextView textView10 = ViewBindings.findChildViewById(rootView, id);
      if (textView10 == null) {
        break missingId;
      }

      id = R.id.textView11;
      TextView textView11 = ViewBindings.findChildViewById(rootView, id);
      if (textView11 == null) {
        break missingId;
      }

      id = R.id.textView12;
      TextView textView12 = ViewBindings.findChildViewById(rootView, id);
      if (textView12 == null) {
        break missingId;
      }

      id = R.id.textView20;
      TextView textView20 = ViewBindings.findChildViewById(rootView, id);
      if (textView20 == null) {
        break missingId;
      }

      id = R.id.textView21;
      TextView textView21 = ViewBindings.findChildViewById(rootView, id);
      if (textView21 == null) {
        break missingId;
      }

      id = R.id.textView5;
      TextView textView5 = ViewBindings.findChildViewById(rootView, id);
      if (textView5 == null) {
        break missingId;
      }

      id = R.id.textView6;
      TextView textView6 = ViewBindings.findChildViewById(rootView, id);
      if (textView6 == null) {
        break missingId;
      }

      id = R.id.view_catetory;
      RecyclerView viewCatetory = ViewBindings.findChildViewById(rootView, id);
      if (viewCatetory == null) {
        break missingId;
      }

      id = R.id.view_on_detect;
      RecyclerView viewOnDetect = ViewBindings.findChildViewById(rootView, id);
      if (viewOnDetect == null) {
        break missingId;
      }

      id = R.id.view_plantdoc;
      RecyclerView viewPlantdoc = ViewBindings.findChildViewById(rootView, id);
      if (viewPlantdoc == null) {
        break missingId;
      }

      return new FirstLayoutBinding((ConstraintLayout) rootView, ConstraintLayout, appbar,
          coordinatorLayout, editTextText, imagebot, imagebot2, imaigebot3, scrollView2, textView10,
          textView11, textView12, textView20, textView21, textView5, textView6, viewCatetory,
          viewOnDetect, viewPlantdoc);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
