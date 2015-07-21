package com.vogella.rcp.eclipse3x.e4view;

import javax.annotation.PostConstruct;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class ViewEclipse4x  {
  private TableViewer viewer;

  @PostConstruct
  public void createPartControl(Composite parent) {
    viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL
        | SWT.V_SCROLL);
    viewer.setContentProvider(ArrayContentProvider.getInstance());
    viewer.setLabelProvider(new LabelProvider());
    viewer.setInput(new String[] {"One", "Two", "Three2"});
  }

  @Focus
  public void setFocus() {
    viewer.getControl().setFocus();
  }
} 