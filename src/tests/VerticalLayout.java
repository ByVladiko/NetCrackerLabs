package tests;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;

// Менеджер вертикального расположения компонентов
class VerticalLayout implements LayoutManager {

    private Dimension size;

    VerticalLayout() {
        this.size = new Dimension();
    }

    // Следующие два метода не используются
    @Override
    public void addLayoutComponent(String name, Component comp) {
    }

    @Override
    public void removeLayoutComponent(Component comp) {
    }

    // Метод определения минимального размера для контейнера
    @Override
    public Dimension minimumLayoutSize(Container c) {
        return calculateBestSize(c);
    }

    // Метод определения предпочтительного размера для контейнера
    @Override
    public Dimension preferredLayoutSize(Container c) {
        return calculateBestSize(c);
    }

    // Метод расположения компонентов в контейнере
    @Override
    public void layoutContainer(Container container) {
        // Список компонентов
        Component list[] = container.getComponents();
        int currentY = 5;
        for (Component list1 : list) {
            // Определение предпочтительного размера компонента
            Dimension pref = list1.getPreferredSize();
            // Размещение компонента на экране
            list1.setBounds(5, currentY, pref.width, pref.height);
            // Учитываем промежуток в 5 пикселов
            currentY += 5;
            // Смещаем вертикальную позицию компонента
            currentY += pref.height;
        }
    }

    // Метод вычисления оптимального размера контейнера
    private Dimension calculateBestSize(Container c) {
        // Вычисление длины контейнера
        Component[] list = c.getComponents();
        int maxWidth = 0;
        for (Component list1 : list) {
            int width = list1.getWidth();
            // Поиск компонента с максимальной длиной
            if (width > maxWidth) {
                maxWidth = width;
            }
        }
        // Размер контейнера в длину с учетом левого отступа
        size.width = maxWidth + 5;
        // Вычисление высоты контейнера
        int height = 0;
        for (Component list1 : list) {
            height += 5;
            height += list1.getHeight();
        }
        size.height = height;
        return size;
    }
}
