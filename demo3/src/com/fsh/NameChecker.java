/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.fsh;

import static javax.tools.Diagnostic.Kind.WARNING;

import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementScanner8;

/**
 * @author fengsihan
 * @date 2019-11-01 21:08
 * @desc 名称检查
 * 程序名称规范的编译器插件
 * 如果程序命名不规范，将会输出一个编译器的 WARING 信息
 */
public class NameChecker {

    private final Messager messager;

    public NameChecker(ProcessingEnvironment processingEnv) {
        messager = processingEnv.getMessager();
    }

    public void checkNames(Element element) {

    }

    private class NameCheckScanner extends ElementScanner8<Void, Void> {

        /**
         * 此方法检查 java 类
         *
         * @param e
         * @param aVoid
         *
         * @return
         */
        @Override
        public Void visitType(TypeElement e, Void aVoid) {

            scan(e.getTypeParameters(), aVoid);
            // 检查驼峰
            checkCamelCase(e, true);
            super.visitType(e, aVoid);

            return null;
        }

        /**
         * 检查方法命名是否合法
         *
         * @param e
         * @param aVoid
         *
         * @return
         */
        @Override
        public Void visitExecutable(ExecutableElement e, Void aVoid) {
            if (e.getKind() == ElementKind.METHOD) {
                Name name = e.getSimpleName();
                if (name.contentEquals(e.getEnclosingElement().getSimpleName())) {
                    messager.printMessage(WARNING, "一个普通方法" + name + "不应当与类型重复，避免与构造函数产生混淆", e);
                    checkCamelCase(e, false);
                }
            }
            super.visitExecutable(e, aVoid);
            return null;
        }

        /**
         * 检查传入的 Element 是否符合驼峰式命名法，如果不符合，则输出警告信息
         *
         * @param e
         * @param initialCaps
         */
        private void checkCamelCase(Element e, boolean initialCaps) {

        }
    }
}
