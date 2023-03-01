package main.java.com.ubo.tp.twitub.ihm.twitFolder.object;

import java.awt.*;

public class GridBagConstraintsCustom extends GridBagConstraints {

    public GridBagConstraintsCustom(int gridx, int gridy, int gridwidth, int gridheight,
                                    double weightx, double weighty, int anchor, int fill, Insets insets,
                                    int ipadx, int ipady) {

        this.gridx = gridx;
        this.gridy = gridy;
        this.gridwidth = gridwidth;
        this.gridheight = gridheight;
        this.weightx = weightx;
        this.weighty = weighty;
        this.anchor = anchor;
        this.fill = fill;
        this.insets = insets;
        this.ipadx = ipadx;
        this.ipady = ipady;
    }

    public GridBagConstraintsCustom(int gridx, int gridy, int anchor) {
        this.gridx = gridx;
        this.gridy = gridy;
        this.anchor = anchor;
    }
}
