/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.FamilyProfileDAO;
import DAL.RelationshipDAO;
import DAL.UserDAO;
import Models.FamilyProfile;
import Models.Relationship;
import Models.User;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author phuon
 */
@MultipartConfig
public class UserProfileController extends HttpServlet {
    private final String DEFAULT_PIC = "iVBORw0KGgoAAAANSUhEUgAAAgAAAAIACAYAAAD0eNT6AAAABHNCSVQICAgIfAhkiAAAAAlwSFlzAAAOxAAADsQBlSsOGwAAABl0RVh0U29mdHdhcmUAd3d3Lmlua3NjYXBlLm9yZ5vuPBoAACAASURBVHic7d15uB5Fnejx78kCJIEQQsISloSw7/u+KasKqGxuo4zb6J1FUVFwu4Le8YqOM6POve4IgjoKKiqKKCDILoKKLCprIBAgYUmA7Mk59486uRxCTjjveftX1f2+38/z1APP4NTpX3d1db3V1b8CSZIkSZIkSZIkSZIkSZIkSZIkSZIkSZIkSZIkSZIkSZIkSZIkSZIkSZIkSZIkSZIkSZIkSZIkSZIkSZIkSZIkSZIkSZIkSZIkSZIkSZIkSZIkSZIkSZIkSZIkSZIkSZIkSZIkSZIkSZIkSZIkSZIkSZIkSZIkSZIkSZIkSVK99JQ+AEmVGg2sPeCfAOOANfr/fQLpvh/4358DlgJ9wNz+/9sSYP5K/33FPyV1AAcAUr2tCUwFNgEmA+sDk/r/ObBM6i/jg4/nGeCJ/vJkf1n5358AHgYeAhYHH4+kYXIAIJU1mvRg3xiYvooyFRhZ7Oja9zRw/yrKo/3/XFju0KTu5gBAymMUsDWwS3/ZGdgJ2JxmP+DbsQyYCdwB3A7c1v/Pe/r/m6RADgCk6q0H7AjsCezQ/++7A2NLHlSDLCUNAu4E7gJuBW4hzRpIqogDAKk9I4HtgAOBg/r/Ob3oEXWuR0mDgeuA64GbSYsVJQ2DAwCpNWsDu/HCB/56RY+oe80H/sTzA4LrSGsOJA2BAwBp9cYCLwNeARxCem/fre/s6245aQ3BNcBlwNW4yFAalAMA6cWmA0f0l1fy/PfyapZFpFmBK/rLrWUPR6oXBwASjCFN5R8BvBrYvuzhKMgDwOWkwcCvSDkNpK7lAEDdaiJwPHAyaYp/zaJHo9wWkV4RXARcjGsH1IUcAKibTCD9wj8ZOIrn0+Oquy0HbiINBr5LymQoSWq4scBxwIWkX319FstqyiLgEuAU4tMqS5IqtgbpV/7FpFXgpR8qlmaWhcCPgJNwtkiSam0b4Gzgcco/PCydVZ4CvkZK4SxJqoE1Sb/2Lwd6Kf+gsHR+uQV4F6Z2lqQitif92p9D+QeCpTvLXNKswG5IkkKNBt5CWrFduvO3WAaWG4E3k9qoJKki6wCnAg9RvqO3WFZXHgXOwv0hJKkt00jT/E9TvmO3WFopzwJfBKYiSRqy3YHzSfvCl+7ILZZ2ynJSXoH9kCQN6hhSetbSnbbFElF+A7wKqSZMBaw6OBr4FLBP6QNpgMXAkyuVOav4vz0LzO///3mONJsCz+e8XzLgv4/j+UQ3K95dj+b5XRDHkdZhrL9SmbyK/5t7Kry0m4BPkD5dlYpxAKCSDiI9+F9e+kBqZCkwE7iftKBsVv+/rygzSPkO6mo90nbKK8oUYOP+f9+ONJhQcgNpIHBl6QNRd3IAoBIOJD34Dyt9IAXNB+4EbgNuB+4A7iY99Ov8gG/HCNKAYGtSRr2dgV2BHejugcEVpIHAjaUPRN3FAYBy2pv04H9F6QPJ7D6ef9Df3v/v99O5D/pWjSDNEOzKCwcGW5Y8qAIuJQ0Ebi19IJJUlW2Bn9AdqXoXAdcBnyNtPTy5gvPXrTYAXgP8G3A93bGbYy/wY9IsiSQ11gTSd/yd3HHPIy3mOgs4AhhTxYnTKo0C9iQlhboQmE356x9VlpDyCKxbyZmTpExGAv+DzszTv4T0OdfpwC5VnTANSw/pVcGHSZ+PLqF8+6i6PA78A+mekqRaeznwJ8p3nFWWx0iJiU4mzWqonsaRZmG+CDxI+XZTZbmL9LmsJNXOlsDFlO8oqyjLgWuADwI7VnmSlNXOwIeAa0nXtHS7qqL8ENiiypMkScM1ls54z99L+i77VGCTSs+Q6mBT4P2kT+2avhh1IfCvuN5EUkGHAH+lfIfYTrmTtICv2z4562abkQZ619HswcB9pFcekpTNROBbNLfzvAM4A6dSlfIPfIT0jr10uxxO6QW+gWtTJGVwHPAw5Tu+VstC0qdjR2ACLK3ansDXSHsnlG6vrZZHgVOqPyWSlPK5/5DyHV2r5U7Sr/2J1Z8SdajxwLtIGflKt99WyyWkVxyS1LYe0i+LJynfuQ21PEP6JXdQwPlQd9mT9FnhU5Rv10Mtc0lrHEYEnA9JXWJTUtKb0h3aUMt9wHt5fhtbqSprkx6q91O+nQ+1XI5ftEgahhOAJyjfiQ2l3EqapRgVciak540grYO5nvLtfijlaeCNIWdCUscZS5ryLN1xvVRZTnrf6WdQKmVPUobIZZS/H16qnI8zY5JWY1/gXsp3VqsrC4Av425pqo9tgK+S2mbp+2N15W7SltyS9P+NJK2Ur/NmKotJC/umBJ0DqV2TSVkxF1L+fhmsLCUlvnJzIUlsDvyW8h3TYGUJafrSpD1qis1Ir9HqnB77BlISJEld6jjSIqHSndFgv1TOAaZFBS8F2wI4l9SWS99PqypPAa8Mi15SLfWQpvzruEPaclLGvm3CopfymkZ6fVXHxYK9pNcW5gyQusB6wKWU73hWVX6F2++qc+1E+ja/9H22qnIJ7icgdbRdSclySnc2K5e7gZMD45bq5AhSiurS993K5R5gl8C4JRXyJmA+5TuZgeVZ0orkNePClmppNCmz4FzK34cDy0LgrXFhS8ppFOkdX+mOZWBZTlrZv2Fg3FITrE/6YqBu6wO+RhqkSGqoScC1lO9MBparSK8iJD1vd+r3Oe7VpAGKpIbZEvgr5TuRFeVp0harPZFBSw22YufNOu3BcS+wbWTQkqq1PzCb8p3HinIJ7komDdVGpFdkpe/bFeVJ4ODQiCVV4iTqk5P80f7jkdS644CZlL+P+0hZDd8UG66kdpxKPZL79JJ+wUyMDVfqeOuSFgnW5b4+KzRaSS0bDXyD8h1EH+mb/kNiw5W6zqGke6v0/d1H2vlwVGy4koZibeAXlO8U+nDPcSnSGNJsQOn7vA/4NWl2QlIhk4E/UL4zmAO8NjhWSckJ1ONLgVtInxpLymwj4M+U7wSuwBX+Um4bUo89Pe7C+1/KanNS3u6SN/4i0o6C7iImldFDWvi7iLJ9wf3A9OBYJZH2GL+f8qP+3aMDlTQkOwK3UbZPeBDYOjpQqZvtRPq2vuSNfi5pMZKk+hhL+eRBs3A7bynEHqTFdqVu7kWk6UZJ9fUuYDHl+omngH3Do5S6yIGU3Tb0YVJ6YUn1txdpSr5UfzGX1GdJatOBwHOUu5mvxm17pabZCLiGcv3Gs8AB4VFKHWw30pRaqZvYPcGl5hoFnE3ZmYC9wqOUOtBOlEv28Rzw+vgQJWXwRmA+ZfqSObgwUGrJVqQVtSVu2Fk4apc6za6U21nwcWC7+BCl5tsMeIAyN+odwNT4ECUVsAnwJ8r0LQ8B08IjlBpsQ+CvlLlBr8DNPaROtw7wS8r0MfcAG8eHKDXPJNIv8BI35rm42E/qFqNIC3xL9DW3A+vHhyg1x3jK7OrXC3w8Q3yS6qUHOJPUB+Tud24hzURIXW808Cvy34SLgDdliE9SfZ1CmcyBl5JmIqSu9mXy33zzgaNzBCep9l4JLCB/P/SNHMFJdfVR8t90zwGH5QhOUmMcAswjf3/0oRzBSXXzOmA5eW+2p4H9cgQnqXH2In/ysV58FakucxCwkLw32uOkZCCSNJjdgNnk7ZsW4r4B6hJbkv8Ge5SUWliSXsp25M8a+ASwdY7gpFLWB/5G3htrBim1sCQN1TTgXvL2VfcCkzPEJmU3GriWvDfU/aTUwpLUqhJpya/GzwPVgf6LvDfSw8D0LJFJ6lRbAo+Qt+/6jyyRSZm8ibw30GxghyyRSep02wCPkbcPOyVLZFKwXci7F/dcYI8skUnqFrsAT5KvH1sA7J4lMinIeuRdSPMc6RNDSaravsAz5OvP7gEmZIlMqtgI4BfkHTG/LEdgkrrWgaQfGrn6tUtIfanUKJ8k302yCHP7S8rjVeTdQOh/5glLqsax5Evz2wu8JU9YkgTAG8i3lfBy0qBDqr1ppJz7uUbHH88SlSS90Fnk6+eexJwmqrkRpEQWuW6Kb2WJSpJerAc4j3z93TXAyByBScPxCfLdDFcBa+QJS5JWaTRwOfn6vQ/nCUtqzV7AEvLcBHfi5zGS6mE88Gfy9H1LSZ8jSrUxjnyb/MwCpuYJS5KGZBr5sgXeA6yTJSppCM4lT8N/DtgzU0yS1Iq9yZf19OuZYpJW6wTyNPg+4HWZYpKk4Xgj+frDkzPFJK3SJuTLj/35TDFJUju+SJ4+8Sn8NFCFjAB+Q56GfhXukS2pGUYD15Knb/w16XNEKat/JE8DnwVsnCkmSarChsDD5Okj35kppo7jyGl4NgHuIn3+EmkJcChwU/DfUWebAEwC1u3/9x7Sr7S1+//7c6TPq/pI20nPA+b0/1MargNJs5ejg//OXGAH4NHgv9NxHAAMz0+BV2f4O/8IfDXD31GzbUDar31b0udYK8pmwPoM//XRMtIal5nAjAHlr6TvvucMs151j/cAX8rwd34EnJTh76jLvY4801rn5gpIjTIJOA74DOn9Z65vr1dVHu0/hs/0H9P6gXGruS4gT3t8ba6A1J0mkqfDvQMYkykm1dt40qemXwf+Qr4d2IZTekmvxr4KHE/8KzI1w1hS241uf4+QXnNJIb5FfCNeRJrOVffaAjidtLHUinfzTSxLSO+AP4jZK7vdHsBi4tucr0wV4nDy/Pp6f66AVCubAacBv6P8gzui9JIWs36AtIhW3ed08rSzQ3IFpO4wFriX+MbrN63dZQRwBHAhzf6l32pZTtpB7mTiV4irPkYAVxLfvv4GrJUpJnWBs4lvtHOAKbkCUlEbAGeR3lmWfhiXLo8AZwKT2zmhaoxNyZM99ZO5AlJnm056Lx/dYM1r3fm2IqVJzbVhSpPKIuB80vfc6mw59k9ZQPoUVmrLxcQ31q9li0Yl7Aj8kHqv4K9LWQ78ANh+WGdaTXEO8W3p+9miUUc6jPhG+jdgXK6AlNU2wHdJD7XSD9amlWWkGYGtWj7raoK1iV9X1QscnCsgdZaRwG3EN9DDcgWkbNYjrRvJ8dlTp5clpBmySS1dATXBy4ifFfsDafGh1JIcm/18JVs0ymEU8C5gNuUfnJ1WngROxV0xO02OVwFvzxaNOsIE0qr8yEY5q//vqDPsBtxC+Qdlp5fbgL2HeE1Uf+sS/zXM45ghUC34T+I7MvNWd4YxpOn+ZZR/OHZLWUr6msK1M50hx/4qn80WjRptW9J7x8jGeFG2aBRpf/IkiLKsutwD7PuSV0lN8FNi28oiYMts0aixfkJsQ3wK2ChbNIowipTIp5uy99W1LAU+Tlq0q+baBJhHbFvxh5dWa2/iV6W6IKXZpgI3UP7BZ3lhuYa0n4KaK3rhdS+we7Zo1Di/JLYBXoW5/pvsSOIXh1qGX2YDLx/06qnuRgDXE9tGfpYtGjXKAcQ2vGXArtmiUZV6SJ+gOeVf/7IMOAMH2k21B/GJs1w3oheJ3qXKfaqbaS1SStHSDzZLa+W7wJqruJ6qv3OJbRuX5QtFTXAwsQ1uHi78a6KJwG8p/zCzDK/cgLsMNtGGxC8IPCRbNDXmNFlyLXBQYP2nAf8RWL+qtzVwKc3LRd8HPADc1f/PGcBDpLULT/aXRaRPXef3//+MA9YgzXas3182IC2qm0baEXN7YAua12fcDbwKuK/0gaglHwY+E1j/VZiGXcDRxI40/0bqXNUcOwOPUf4X7FDKw6Td895LykuwTsD5WGEd0lqZU4ELic/gVlWZRdqRUc2xFmnQFtkuHAB0uR7gd8Q2suOyRaMq7AU8QfmH1mBlIelrlVNJuw2Wti3wPtJ71UWUPz+DlTmkBWZqjhOIbRPX5QtFdXQMsQ3sV/lCUQUOAOZS/mG1clkMXAK8BRgfFn371gVOAX5OfDbN4ZS5pFkSNcdviG0TR+ULRXVzNXENaxlOOzbJXtTv4X83cDppUVTTbET6HO8eyp/HlQcBzgQ0x67EfhZ4Rb5QVCd7EdvRXJAvFLVpZ+o17X8d6dVR0xbcDeYg0gxGdJbNoZY5ODhvkh8Q2x7MDtiF/pu4BrWUtIpc9bc19Vjw1wv8mLStcKfag7TXRh0GArNwc5im2J7Y3Tb9sdZlphKb1e2cfKGoDZOoxxT1pcCewbHWyV6kRYOlz/tfSbkeVH/nE9cOluA+El3lP4ltTNPzhaJhWos01V76AXRMdKA1dgRwO2WvwTWYMbAJtiL2R9u/5QtFJU0AniGuIX0lXygaph7KpvedD3yAtK1wtxtNWui4gHLX47t0znqLTvZN4trAXOr9hY0qcjpxjWghsGm+UDRMH6bcw+ZynCFala2I349jdeVD8SGqTVNJn8RGtYHT8oWiEkaT0qJGNaAv5AtFw3Q4sQuKBisLSZ/FjYgPsbF6gHeRZkhyX59l+E14E3yZuDYwk/SMUId6C3GNZwFu+FN3U0mfgOV+uNyGn521YmfgDvJfp9m4GKzuNiENpqPawN/lC0W5Rab9/b8Z41DrRpF2h8v9UPkeabMdtWYMcB75r9d1wMj48NSGbxB3/W/IGIcy2oW4RrMcv/uvuzPJ+yBZRsqRr/acRmwmuFWVj2eJTMO1PbG5JHbKF4py+S/iGsyPM8ah1u1N3tz0i4HXZYmsOxxP7LTvymUpsF+WyDRclxB3/V3L1WHGAE8R12AOyheKWjQWuJd8D495wMuzRNZdDif2892Vy92kfkP19DLirv2TpDwh6hCRi/9uzhiHWvd58j00HsONZiLtCTxOvuv5mTxhaZhuIu7avzFjHAp2DXEN5eSMcag1e5Hvk78HSN+yK9bWpHOd45ouxY1i6uwNxF37qzLGoUDbEbdg5H5cMVxXo4A/kedB8Rg+/HPamnwzAbfgPV5Xo4gbDPbiwu6O8O/EdQ7vyRiHWvMe8jwg5uG0fwm7AE+T5xq/O1NMat37iLvuZ2eMQwHWICX3iOr4184XilowkbSQJ/rBsAgX/JV0OLGpYVeU2aQ9RFQ/6wDPEnPdH8PMgI32OuI6ha9mjEOt+RLxD4VlwEm5AtKgXk+ePAH/nisgtezrxF33EzLGoYpdTFzD2CtjHBq6bcjzzb9JfuojcoOvFWUxsGWugNSSfYi77hdljEMVWoe45CG3ZYxDrfku8Q+D72aLRkPRA/yE+Ot+XqZ41Lo/EnPN5+Or3kZ6M3EdwT9mjENDtyPx08G3kZILqV7WJ3anzz7Sa59tcwWklvwLcdf9DRnjUEWiUkUuwAVBdfVDYh8AC0k71ame9iX+9c/3skWjVkwgbhvpizPGoQpMIK3QjmgM38oYh4ZuW+J//f9Ltmg0XGcQ2waWk3KLqH4uIOaaLwLWzRiH2vRW4jqAA/KFoRZErgTuAy4nvWtWvfUAVxPbFr6cKxi15BDirvmbM8ahNv2SmEZwV84gNGQbkF7NRN3883EFeJPsQOyrgAXA5GzRqBV3EXPNL8kZhIZvInE3/xkZ49DQnUVcZ98HfCBbJKrK54htE5/IF4pa8DFirvdiXPvVCO8k7qb3V2D9jAIeIe6a34XZwJpoLDCDuHbxMO4RUEdbEnfN35ovDA3XZcRcfLf9rafXEnfD9wFH5wtFFTuJ2LZxbL5Q1IJbibnev8gZhFo3jrjV/6dljEND9wviOvhLM8ahGFcQ1z5+mjEODV3UlyALgDEZ41CLjiXmwvcCm2eMQ0OzGSk5S9Q13zNfKApyMHEDgKXAxvlC0RBtQdwW8K/MGEe4EaUPoGJRF+dGUpYx1cvriXsP+xPSVKKa7dr+EmEUcHJQ3Rq+B4DfB9X9iqB6VYH7iBn1nZozCA3Z74j7dbdbxjgU6yji2sn1GePQ0J1GzPW+O2cQGrptibngy4FNMsahoYmc5rs8YxzK42Zi2kovMDVjHBqazYjrHzrma7BOegUQNTVzPekzM9XLScRl5vuPoHpVzqeD6u3BPePraCbp1W2EjnkN0EkDgKj3/z8LqlftOSao3ntIn5Kqs/yM9IowQlRbVHui+u6OWgjYCcYQlwp2x4xxaGjGE5ft8fSMcSivjxLTZhYD62SMQ0OzKzHX+zlgrYxx6CW8ipgLPTNnEBqyE4m53kuAjTLGobw2Im7g+OqMcWhoekgZGyOu95EZ4wjTKa8Aot7JmAimnqKy8/0KeCyobpX3GHBlUN0d8164g/SR7ukIHXG9O2UAcGhQvb8MqlftibrePwiqV/URdY0PCapX7Ylaz+P1ronxxGSDW9Jft+plEjGf9yzE690NJpDe2VfdfpaTdiJVvUwgZWys+novJaWeb7ROmAHYn5hscNcBzwTUq/YcQMznf1fj9e4Gc4FrAuodQeqLVC9zgZsC6h0F7BtQb1adMAA4IKhePwWrJ6+32hX1au/AoHrVHq/3IDphAHBQUL2+/6+nPYLqdcFn94ga7O0eVK/aE3W9Gz8AaLqRwLNU/35nDnFZ5tSex6j+ej+cNQLVwSyqb0dmDK2nHuBJqr/e84jbjCyLps8A7AqsHVDvdaQLrHqZDGwYUO91AXWq3m4IqHMKqY2qXvqISQs8noYnimv6ACBqCiaic1D7onbo83p3n6hd/HYKqlftibreUa+gs2j6ACBqQZgPhHraJqjeqL3DVV83B9W7bVC9ak/UAKDR6wCaPgCIOPmLgVsD6lX7pgXU2QvcHlCv6u0OYl7zTQuoU+37PSm3S9UcABSyEWnP56rdAiwKqFft2yKgzvtJm3uou8wDHgqod1pAnWrfQuAPAfVOBTYIqDeLJg8Aot61Of1fX1MD6vxLQJ1qhrsC6owYpKoarvtYSZMHALsE1RvVSNS+iBmfGQF1qhkeCKgzoo2qGlF9e9SzKJwDgBfqwxmAuuoB1g+od0ZAnWqGGQF1TsIcInUVNQDYOajecE0eAESc9PtJSYBUP+uS8m9XLeI9sJrhwYA6RwPrBNSr9s0mZtbHGYDMRgE7BNT7p4A6VY1JQfXODqpX9Rc12I+YqVI1/hxQ5440NCNgUwcA2wBrBdR7R0CdqkbUVqtPBdWr+nsyqF4HAPUVMQAYA2wVUG+4pg4Aot65RDQOVSNiwAdxDwHVX9S1HxNUr9oXlfOjkesAmjoAiHrn4gCgvtYMqndBUL2qv6hrv0ZQvWpfVB/fyHUADgCeN5+0CFD1FDUAiMgOpmZYHFRvVFtV++4lZuDnDEBGETsw3UFKC6t6ivpV5QCgezkA6D7LiUkA1chkQE0cAIwGNg+o97aAOlUdt2dWU9hW6y1iHcBUGvglQBMHAJsRc6LdEKbeovZn8H1t94r6pe5eIvUW0dePBjYJqDdUEwcA04Lq9RPAeovqVJ2u7V4OALpT1I+9xu0D0cQBQNRJvjeoXlVjYVC9Y4PqVf2NC6o3qq2qGlF9vQOADKYF1LkEmBVQr6oTtWArKsGQ6i8qYY8DgHp7GFgaUO+0gDpDOQBIHsQvAOou6pvtqBTDqj8HAN1pGTAzoF5nADKYFlDnjIA6Va2onP0OALqX+0t0rxkBdU4LqDNUEwcA0wPqjNghStV6mphfVhGflKoZpgXUuQCYF1CvqjUjoM5pAXWGatoAYE1go4B6ZwTUqeo9HlDntIA61QzTAup8NKBOVS/iR98mNOyz4qYNAKYSc8wzAupU9SI618a9t1NlIq79YwF1qnoRA4CRNGxGsWkDgE2D6vUVQDNEDAB2CKhTzRBx7Z0BaIYZQfVGPaNCNG0AELVoxwFAM8wIqHMLYHxAvaq3dUlZRatmX9IMUdcp6suSEE0bAESc3MW4arcpIjbx6KGhG3moLTuTrn3V/hJQp6r3KDG5ABr1VVHTBgARJ/cJ3LyjKf4aVO/eQfWqvvYNqtcBQDP0AU8G1OsMQKCIkxvRCBTjzqB6DwyqV/UVdc0dADSHA4DSB9CiiBkABwDNMZeYVdYOALrP/gF1PoI5AJokou/3FUCgiLztDgCaJWInrynAtgH1qp52JCafiDuKNssTAXU6AxBockCdEY1AcW4MqvcVQfWqfl4ZVG9U21QMZwBKH0CLIkZXDgCa5YageqMeCqqfqMFeVNtUDGcASh9Ai1wDoBuB5QH1voz0bbg62wTg4IB6e4GbA+pVHGcASh9AC9YA1g6o1wFAszxDTD6ANYHXBNSrejmBmHztt+MCwKaJ6PvXBUYH1BuiSQOAtYlJ3PFUQJ2KdX1Qva8Lqlf1EXWNnf5vnogBQA8wLqDeEE0aAETtsuSovXkuC6r3KGJWh6sepgCHB9Ud1SYVJ6rvXzOo3so1aQAQdVIXB9WrOFcAiwLqHQ38fUC9qoe3AaMC6l1IapNqliVB9TZmS+AmDQCiTmpEPmjFmg9cHVT3O4l51aSyeoC3B9V9NbAgqG7Fifrx5wxAAGcANNAvgurdCj8J7ETHAtOD6v55UL2K5QxA6QNoQdRJjWoEihU1AAA4LbBulfHBoHr7iG2LiuMMQOkDaIEDAA30AHBTUN2HAXsE1a389gUOCar7RuDBoLoVK2oA4AxAAF8BaGXfCaz7E4F1K6/Ia3lBYN2K1fWvAJrkSNJ0W9XF7G/NNZH0NUBEu+gD9skXioLsTcrSF9E+FtOw1K96gfWIaRdH5AyiHU2aAfArAK3sKeCXgfV/KrBu5XE2cV91/BwziTaZawBKH0ALfAWgVTk/sO6jgVcF1q9YryOt54gS2fYUr+vXADTJCcRM14zMGYQqN4q0CCvqNcA9eEM30TrAw8S1ixnEJBVSPiOJaRsn5gyiHU2aAXC0plVZBnw5sP6tgPcF1q8YZwGbBNb/f0htT80V1ffbLgK4CFCDWQ94jrhfewtIAwE1w46kFd5R7WE+aQGqmm0CMe3jmJxBtMMZAGcAOsHTxH6ONQb4GqYIboIRwFeI3ZL1XNxFtBNE9f3Lg+rtavsSM1qLnCZUPtuRbryoX319wKnZotFwfYTYNrAM2CZbNIq0KTFt5MicQXSL3Yi5WFvkDEKhvkts578Q2DlbNGrVwaTPeiPbgCv/O8d0YtrIy3MG0S12IOZibZszCIXahvgHhVnGDAAAIABJREFUwB3AuFwBacgmATOJvfZLcS1IJ9memHayX84g2tGkNQCmbdRLuZv41Kw79v8N1wPURw9wDmlKN9K3gXuD/4byier73Ro6wGbEjNb2zBmEwk0jLRiN/CXYhzsG1kn0e/8+Upualike5bE3MW1l65xBdIsNiLlYB+UMQll8kfgHwnJSpjmV9UbiF3/2AV/IFZCyOZiYtjIlZxDdIuqbzeNyBqEsxgOPEv9QWEJKF6wyDiN2M6gV5TFS/6PO8lpi2ktj2kqT1gBEvVcxoUfneQb4WIa/Mxq4CF8jlbAX8FPybLxyBjA3w99RXusF1NlHShSlAM9Q/WjtA1kjUC4jgBuJ/3XYB8zBr0ly2pL0qzzHtb0BF3x2qg9RfXt5OmsEbWrSDADAEwF1OgPQmXqB95InK9ck4DJc/JPD1sCVwIYZ/tYy4J9JHbs6T8QMQKO2h3YAAOsH1Kl6+D3w+Ux/axppxqEx3wA30F7AdcDUTH/vbOCPmf6W8psUUOecgDrV71Kqn7L5QdYIlNuawJ/JM13cBzyLCwMjHAbMI991/BPmCOl0P6T6dnNJ1gja5AyAMwCdbjHw96QsbjmsDfwMeEOmv9cNTgB+Qfq6I4elwDuISz6meojo+yOeUWEcADgA6AZ/BD6d8e+tQdqX4HRcQNaOHlKSn4uAtTL+3U8Ct2b8eyojYv1Xo9YANM1HqX7K5qGsEaiUUcBvyTeFvKL8HAeZwzGJmFd+L1V+A4zMEJ/Ke5jq28+Hs0bQZd5N9RdsCd7w3WJD4BHyP1RmYsbJVuwDPED+6/QosHGG+FTeKGI2DntnziC6zYnE3PjRm4ioPg4gDfpyP1yWAmfRvNduOfUAp1Lm+iwhpYZVd5hKTDt6bc4gus0hxFw0f511l4gEIEMtV5N2FNQL7QxcQ7nr8r74EFUjPksaKGr/5r/LGYSK6wG+Q7mHzRLgc6QvBrrdOsC/EzMdO9RyXnSQqp23ENOWzAgaaAwpw1vVF+0jOYNQLYwGLqfcQ6ePtB7hlOhAa+w44EHKXoOryLOfgOrlf1J9W+ol79cqXSlil7evZo1AdTEBuJOyD6A+4Aq66/3zfqR0vqXP++3AusGxqp6+QfXt6ZGsEXSp66n+wv0yawSqk6nALMo/jPpInyl2chbBfUgJfUqf5xWd9Wax4arGfk31berarBF0qYh3t3dljUB1szMph3fph9KKcjPwGjojiVAPaaq/RA6GwcpsYKfIoFV7f6P6dnV+1gi61Keo/sK5f7N2JWWaLP1wGljuJa1PaeK36VNIibvuo/x5HFjmkAZ86l49wEKqb1tnZYyha72NmI7BXADanZTKs/RDauWylPSa6q2kdQt1tR7p/ryMsqv6BytPkAZ66m5TiWlf3bygN5tDibl4r8gZhGprT+Apyj+sBiuLSe8vPwDsEHQOWrEjcBrpi4rFlD8/q3v47x50DtQsxxDTxrppIW8xmxNz8U7LGYRqbUfSHhGlH1pDKY+StjV9P3Agsava1yUlOnk/8CPgsYJxt1JmkHKISJA26YpoZ5vkDKIKTVxkNBJYQPV7dZ9Hmr6UIL0SuoxmZu2bAfyFlE9/BmkwM5v0euNJ0v2zDHi2/3+/Dik3+ljSxkXrk/ZN2AzYor9sT5o6bZrbSbN7s0ofiGrj21Q/Xb8IGEfKBaBgd1P96O3mrBGoCSZQr9XrltbKDbgTo17sVqpva3/JGkFFmroxyX0Bde5Ac8+HYswlfZd/QekDUcu+DRyG+7PrhUYA2wXUG/FMCtfUB97tAXWOo5lTnIq1iDRd+G5SDn/V2zLSnuxvJV07aaAtSa+6qvbngDrDNXUAcFtQvSYH0WC+DhxOWvimepoDHAV8tvSBqLai1vREPZNCNXUA8Kegeh0AaHWuA/bGlJ919FtgN9LmPtJgogYAjZwBaKpRxGRyuihnEGqsHuBU6v3de7eUJaQMbCNXd8Gkfj+m+ja4ANtfdrdQ/YV0Nye1YifS1F/ph2C3lruAPV7yKknPe4Tq2+HvskZQoaa+AoCYdy5TMCWwhu4u4GPAM6UPpAs9Q0roEvU6UJ1nKqmPr1pjp/8dALzYfkH1qnPsSJp2vg+4BBhf9Gi603jSuZ8JfJGUoVBanf2D6nUQWsChxEwrfj5nEGqMTUjv/SOSiFiqKXeSBmZbrPoSqst9gZh2d2DOIJRMIKVdrPpiXpczCNXaKOAk4ApgOeUfcJahleWkzYlO7L+GEsCNVN/WenEGsJgZVH9BF1L9PgNqlgmkX/szKP8ws7RXZpFmBSahbrYmKTFU1e2rkRkAO0XEJx19pG+91X12B84h5hNTS9myAPgmKVeAus/+xLSrH+YMompNXgQIcdP1LgTsHiNI0/zXAn8A3g6sVfSIFGEM8A7gj6SkQSfS/P5PQxfVp5sUrKC9iRnV/ShnECpiBHAc6YFQ+teppUy5k7TPg0lcOt/PiGlD5qEoaBTpe+CqL+rTuHioU40ATiZ9w1/6AWSpR7kLBwKdbBQwj+rbzTxsM8VdTkyn4GuAzrLiwf8Xyj9wLPUs9wHvwsF/pzmYmPbyy5xBROiEd2BR72COCqpXefUAbyA9+C8kZi9wdYbpwNdI242/jtR21HxHBNXr+/8aeDkxo7trcgahEHuSrmPpX5aWZpbfEZc9TvlEfP/fR5pZUGFjiNmVbSkmeGiqKaRfcibvsbRbekkzR5ujJppA6surbheL8Guh2oga4R2XMwi1bQxwBjELQy3dXeaTEgqNQU1yPDHtoSNmiDtlscu1xCzaO5K04Yjq7/XA5+juX2rzSZvjzAbmrlTm9/9zhWeBZf3/PhpYe8B/mwCM6//nirIeMBnYrP+/dZuxwJnAW4EPARcVPRoNle//V6NTFrkcR/rOs2p/w0Vjdbcp8FXgmNIHksFi4B7g7gH/nEna4/xh8m1LPJ503jchDbi2BrbpL1uR0q52ukuAfyKdd9XXPaQ2WbVXApcF1KthGA8sIWaqZ4eMcWjoeoD/Qcz3vXUoDwE/BT4JvJa0w10TvtoZSVpNfzzwKdLAfCblz2dEmQu8m875IdVpdibmui+iO2fBau1KYi72x3MGoSHZmpTOtfQDoMoO5TrgbOBYOnPjmsmkmbrPAtcTs3C3VLmKmF+Zas+ZxFxvf/nX0GnEXOw/5AxCqzWKtMhvAeU7/XbKMuAGUgd1MN25mngMcAhpYd2NpHNS+rq0UxaQ1gZ0yrqqTnAbMdf6vTmD0NBsR9zNvUXGOLRq2wC/p3xHP9wyi7Qb3cmkBXV6oYmk5DvnAI9S/noNt9yMswF1sBVx13jLjHGoBfcQc8FPyxmEXuQU0qr10p17q+UhUj6C4/CXYStGAAeRXoncS/nr2Gp5Bnhz5WdFrTiDmGt7V84g1JovEHPRr88ZhP6/tYHzKd+ht1IeJ7XDfXFxWBV6SJ/4fpH0eWPp69tKOY8Xfl6pfG4i5pp+PmcQas1RxFz05aTscspnD9JnbqU78aGURaTPwk4mfVOvGCNJ33WfT8prUPq6D6X8Ddg94mRoUJuQMjhGXM+X5QtDrVqTuKnif8oYRzfrAU4lPVRLd95D6dzfh+/0S5gIfIBmDBIXAe/BGaFc3kPMdZyLA/zau5iYi98RqR9rbh3gJ5TvsFdXlvUf45HYodfBCOBoUs6Eun9J8GN8JZDDdcRcvwtzBqHheScxF78XV39Gmk7ahrV0Jz1YeY70bn9q1AlQ26YBX6Lerwf+jF8VRdqauOn/t+YLQ8O1MXG7wH0yYxzd5OXAE5TvnFdV5gCfANYPi15Vm0TKL1DnNnVoVPBd7l+JuWbLgA0yxqE2RGUFfIBmpGNtkn8iLo1zO+Ux0lqEsXGhK9g44P2kLzNKt6eVyxJSKmtVZwTwIDHX61cZ41Cbol4D9JF+rap9o0ifdpXuiFcuT5J+PY4Pi1y5jSN9F/4U5dvXyuVruLCsKkcQd53+PmMcatN6xOUa/3bGODrVROBqyne+A8uzpFc868aFrcImAP+LtJ6jdHsbWH6DX5JU4QJirs9C7BcaJ2o1+XOk1eoano2Iy9E9nNJLWt27eWTQqpUppF/edfpq4E7SFssanrWJ+wT8ooxxqCKvJ+5mfVvGODrJFsSlax5OuZGUZU7daQ/qtavkA6RV7GrdO4i7LidkjEMVGUvciPDajHF0it1JC+tKd7J9wMOkrH0SwBtImzWVbpd9pI2QdosNtyNFffs/j+7crbMjRL0T6gP2zBhH0x1CyqJVunNdTpr6dYGfVrYuaVFqHV4LPEta0Kah2Z24a/GtjHGoYq8irmF8M2McTXYsaa/00p3qn0gb9Eirswf12HZ6EU49D9W5xF2HIzPGoYqNJiXdiGgYCzA5zEt5I7CUsh3pYuBjuB2vhm40cCbl81MsJb2e0OAmk1bpR5z/x7DfaLz/S9wN+uGMcTTNiZR/+N8G7BodqDrWHpRPT70UZwJW56PEnfsvZoxDQXYjroE8iCPEVXk1ZX89LQM+DawRHag63prAZym7NmAx6VWaXmg0MJO4875TvlAU6UbiGsmJGeNogiOJm5IbSnkM39upei8DHqHsIOCY6CAb5g3EnW+/9Oogf09cQ7k6Xxi1dzhlF/xdQUo0JEWYDPyCcu17AaYiH+h64s7132WMQ8HGELszmN/tpk/9SqVYXQKcDvSER6luN4K09qfU+pZngQPDo6y/PYk7x3NIr37UQf6duAbz/Yxx1NGewDOU6RAfx61Vld/LgNmUafPzSN++d7MfEnd+P5cxDmWyFSnve0SDWQZsmy+UWtmE2IU4qyt/AKaFRyit2qbAzZRp+7Po3v0rticl9Yo4r72YjrljXUHcDXlevjBqYzzlNvb5Linds1TSWsQmollduYO0w2G3+Q5x5/SyjHEos5OIazhLSZvddIvRxA6oBiu9pPf9Up18hLgZxtWVX5PuxW6xFbGfZB6fLxTlNoq0EUxU4/lqvlCK6qHMr55FwJsyxCcNx0mU+QT2nBzB1cQ5xJ3HWXTXYKornUVcA1pMd+zp/Qnyd3JPAAflCE5qw6HAU+S/Pz6WI7jCphKbYOwT+UJRKZOB+cQ1oi/lC6WIN5N/qvMBuneRpZpne1KW0Jz3SC9p741OFpnW/TlgUr5QVNJ/EdeQFtC5yWh2J3+in7/QHbMq6iybA3eT916ZT+fufTGF2NcrX8gXikrbjNippK/kCyWb9YB7yduh3QFsnCM4KcCGpG2oc94z99CZXwZ8g7hztoT0ekFd5ALiGtQy0jRgp+gBLiZvR3Yzbres5psA3EDee+dndFZWzG2Jzbx4br5QVBeRyST6gB/lCyXcx8nbgd0ArJMlMineOOAa8t5DZ2SJLI+fEneeeoEd84WiOrmE2JuwE3J2H0berVBvJb1ukDrJusDvyHcfLaMzdsU8mNjzdHG+UFQ3+xHbuG6k2VNxm5E33/kfgYlZIpPymwD8nnz30+OkVN1NFrnjXx9wQL5QVEfXEtvAXpMvlEqNJP7mG1huw4e/Ot8k4Hby3VfXkO7lJjqB2HNzVb5QVFfHEtvI/krKQNg0Od/734+r/dU9ppByW+S6v5q4HmAUcBex5+XobNGotnpIu8pFNrR3Z4umGruRshrm6Jxm4+5b6j7bkfadz3GPLQJ2yRNWZf6Z2HNyM81+PasKvYLYxvYkKQNhE6xJvh3+5gP75wlLqp29gWfJc6/dQdq5sAnWJ6X+jjwfh2eLRo1wFbEN7hv5QmnLZ8nTIS0FjsoUk1RXryLfVzafyRRTu84l9jxcni8UNcU+xOa4X079f+0eSL7O6J8zxSTV3fvJc88tBw7JFNNwHUhsP9wL7JstGjVKdLa7P1PfBYHjSGlEc3RE38wUk9QUXyXPvXcf9U2yNZL0KXBk/Bdmi0aNswPxv4Dfky2a1vwf8nRAV+Ce29LKRgNXk+cerOuOpacSG/dS3FVUL+FcYhvh06RNQupkL2LTIq8o9+G3/tJg1ifP54HLgD0yxTRUGwPziI27KeuwVNAmxG95e362aF7aCOAm4judhcCemWKSmmo38my5/XvSvV8X3yW+/9ksWzRqtP8gtjH2Aq/MFs3q/SPxnU0f8I5cAUkNl+ue/IdcAb2EI4ld+NdH+rpJGpJJxE9HPUTaIKSkDYCniO9ozs0VkNQhvkP8ffkkqa8raQIwk9g4n8ZXj2pRjk9zSq+GP4/4GG8HxmSKR+oU44hPhdsHnJMroEGcS3yMdV14rRobRZ6MeKVeBRxE/LTbImDXXAFJHWZ34lNy91JuR7xjhniM7ZTbqe+n16q5Q4l/SM4k/6uAkaScBNE332m5ApI61OnE36d/JP+CwPWARyo49pca3ByUKyB1pu8RfwN+K1s0ydsrPPbBym+p1ypjqYlGAFcSf7+ekiugfudXeOyDlW9ni0YdawrwDPGN9VWZ4lkLeDA4lqeATTPFI3W6zUgL2SLv2QeANTLFc1xwLH3AXGCjTPGow32Q+Ab7MHlW5OZY3Pi2DHFI3eSdxN+3780QxwbArAyxvC9DLOoSo8jzzvxSYveoXgd4PDiGK4NjkLpRD/BrYu/dOcD44Bh+FhxDH2nrY9ONq1KHEL8gsI/0Cz3Kp4KP/TlgeuDxS91sGvAssffwmYHHn2NBYx/wssAY1MW+T3zjXQzsHXDsGxDfeeSYQpS62QeIvYefASYHHPd+wJLgY+8jJVCSQkwmfgq9j7RpTtWfBn4p+Jj/QPq8UFKcHFvm/mfFxzwBuD/4mPtIrzA2qPjYpRd4I/ENuY8021CVjUlJeaKOtRfYv8LjlTS4g4l9HbmQalfQXxR4rAPLyRUeszSoH5KnQVe1WcfZwcd5XkXHKWloovcK+HRFx5lrY6MfVHS80kvakDTdFN2oF5C2B23HeGK/IZ6H39tKuUXnJ3mK9NVQO/YgzSZE95OziVm3IA3qDeQZ2T5Ae/kBonMYfLCNY5M0fGcQe2+380XSBsQnHFtRnPpXEbnebV3L8LJ0jSZtOxx1XDOANYdxXJLatxax9/dMht/vXBV4XAOLU/8qZhJ5vgroA/5rGMf3tuBjyp0/XNILvYPYe/wtwzimrwQf04riqn8V93ryNPY+WlsU2EPKiBV1LH/GzX6k0kYSf5+3ktnzrYHHsnI5qYXjksLkehWwiKF/bnds8LHk2rxI0uq9mth7/ZVDPI4DSYnMcvSFVX4mLbUlV6KLPuBRhrbT3i8Cj+Gm1k6PpGA3E3e//2wIf38K8EjgMQwsDwITWzs9Uqz9yZPqsg/4PbD2ao5lM2BZ4N8/ZlhnSFKU1xB3vy8FNlnN314HuDXw7w8si4F9hnWGpGDRn+UMLJeSdilclTMD/+4fcbc/qW56iH0If2yQvzsauCzw765cPjDsMyQFy7Xd5YryHV78MB5B+jwv6m8e3+Y5khTjZOLu+/t58aLfHuBbgX9z5fIL/PGhmptMvndhfaQtfgc6KvBv3YE3oFRXI4C7iLv/j1zp7/1r4N9aucwE1m/7DEkZHELsO/iVy78M+NsXBv6dd1RzeiQFeRdx9//ApDv/EPh3Vi7LgMOqOT1SHmeS9wY5njRCjtr1bzYwptIzJKlqY4jbp2QxaYbzWNLCwFz922DrD6TaGgn8hnw3yXzggsD6V37VIKmePk1cP3ABqa/J1a/9GhOOqaE2Ir27ynWzRJXFwMYVnxtJMaaQ75PkyPIgpvpVw+1J2ta39M3UTvlO5WdFUqT/pny/0U55jva3Qpdq4USgl/I31XDLodWfEkmBDqd8vzHc0kvabl3qGJHv5SLLvfjpn9Q0PcA9lO8/hlM+GXA+pKJGAD+l/M3Vajkj4mRICvcxyvcfrZaLcdGfOtTapO01S99kQy1LcfGf1FQb0azFgHcC40POhFQT04EnKH+zDaVcEnQOJOURuStolWUOMC3mFEj1cjD59s1up7wp6gRIyuItlO9HXqosAV4edQKkOopM2VlFWUDa5lNSc40HFlK+Pxms9GKKcXWpMyl/Aw5WBub9ltRcP6R8fzJYMc2vutoXKH8Trqq47a/UGU6ifH+yqvLlyKClJhgJXET5m3FgmY8b/0idYiz1y0b6ffzcTwJgDdKmF6VvyhXlp7HhSsrs55TvV1aU3wBrxoYrNct44FbK35x9pAWKkjrHuynfr/QBtwETgmOVGmky8DfK3qC9wKbRgUrKagrl9yO5l5ScSNIgpgOPUu4mvTU+REkF/IFy/cpsYOv4EKXm241y2QJnAfvEhygpo71J93aJPmUOsEt8iFLn2JV045S4YRdhcg6pU/wd5b4CmI0Pf2lYSg4C+oCvAaPDo5QUYRRwNuX6Dx/+UptKDwKuBTYMj1JSlSYBV+LDX2q8XSg7CJiJ6wKkptgNeICyD/+dw6OUukjpmYAFwDvDo5TUjndRdvMff/lLQUrPBPQBPwLWiw5UUkvGA9+jbN/gL38p2C7A45S90e8lfVYkqbx9gPso2yc8jg9/KYvpwD2UveGXAmfhhh5SKT3AqcBiyvYFM4BtY0OVNNBGlM3staJcjuk9pdwmUY8Nfu4CNguOVdIqrAtcRflOYBZwTHCskpLjKJfVb2C5EZgYHKuk1ViLtDCvdGfQB5xLGpRIqt4E4DzK3+d9wGXAuNBoJQ3JSOArlO8U+kgbGR0XG67UdY4GHqL8/d1HGoSYIVSqmTMo3zmsKBfi54JSu8aTUnKX3sq3r/8YziItPpRUQ/8ALKF8Z9FHWh18ZGi0Uuc6GniQ8vdxH+lLg1Niw5VUhQMpnytgYLkEVwpLQ7UxcD7l79sV5RngFaERS6rUdOB2ynceK8pc4D2k9QqSXmwk6bv+eZS/X1eUe4DtI4OWFGNt4GLKdyIDyx+B/SODlhpoD+B3lL8/B5bfApMjg5YUawRl9wRfVVkOfBkXCUrrAV8l3ROl78uB5T+BUYFxS8roDaQd/Up3LAPLk6QvF9YMjFuqo9GknfvqtFanD1gEvD0wbkmF7Ac8TPlOZuVyN3BCYNxSnZxI+b08VlUeIvURkjrUJOBSync2qyo3AQfFhS4VtTfpvXrp+2xV5Spgw7jQJdXFCOBjwDLKdzwrl17gv4EtwqKX8poO/IB6JPNZ1f32v/DrHKnrHAI8QvlOaFVlCelb6C3DopdibQ58kfRevfT9tKoyDzg+LHpJtTcZ+CXlOyMHAuoUdX/w95F28psedQIkNUcPKQHJUsp3TA4E1FRNePAvJeXzd8pf0gu8HJhJ+U5qdWUx6btpBwKqi62Ar5PaZun7Y3XlPlzlL2k11iXtPla6s3qpspy0x8ARMadBekl7kmal6jxztqJcCEyIOQ2SOs3x1C9JyWDlJuBknNZUvFHA66lf2t7BylzgjSFnQlJHmwz8iPKd2FDLA6TMgv7SUdXWIa2TmUH5dj7U8kvchVNSm04GnqB8hzbU8gxpatbXA2rXnqRXYs9Qvl0PtTxNSjMsSZXYCPgZ5Tu3VsudpFmBidWfEnWo8aQH6K2Ub7+tlp8Dm1R/SiR1ux7gbcAcynd0rZbngG/hKmgNbn9SG5lP+fbaapmD7/olZbA+8E3qmdp0KOUu4BPANlWfGDXOtsCZwF8o3y6HW74HbFD1iZGk1TkQ+DPlO8B2yp2kxCjmFegem5IW9F1H+fbXTrkbOKricyNJQzYa+BBpir10h9hOWQ5cC/wTab2DOssU4F9ID/2mzlytKM8Bp5PuPUkqbgpp5X3pzrGqcidwNml74p4Kz5Py2ZG0ALQTHvoryiXA1CpPkiRV5Vjgr5TvKKssD5M+BTsOGFvdqVLFxgKvJqXlresul8MtfwOOru5USVKMUaSp9KZkEmylLAAuAz5CWjU+qqJzptaNBg4APgr8GlhI+fZRdZkDvAen+yU1zDjSFOyzlO9Io8p84HLSQsIjgDWrOHFapZGkxDxnkKbC51L++keVxaSdBc1qKanRNgXOIy20K92xRpdnSQOC/w2cQNoiVsMzFTgR+AxwJc1faDqUshy4ANuNMnBxk3LaDfg3ui9F72zglgHlVmBW0SOqnymkX/d7A3v1l8lFjyi/q0hf1Nxa+kDUHRwAqIQjSclXDix9IAU9TVrYdVf/P//SXx4g/QrsRKOALYDtge1ISXh26P/3bp7qvob0CumqwsehLuMAQCUdRsrId2jpA6mRJaQEL/cDD5G+QHi4/99nkmYOlhQ7utVbg5SHflPS9P3Af58ObN3/v1FyLenB/5vCx6Eu5QBAdXAoaSBwWOkDaYA+4DHSoOBJ0kzCyuWp/n8+RxoszO///11M+ooBYBFp1TzAGGCt/n8fy/MLGceRHtjrAOutpkwiPew3wj5lKK4jzYD54JekfgcCv6L8QiyLJaJcBRyOJGlQ+5G2Hu6GrwYsnV2WAt8nLWqUJA3RlqR0vE9TviO3WFopz5KyR7rjpCS1YR3gXTR7y1ZLd5THSQv7JiJJqswI4LWkd6mlO3qLZWD5LfBGzAQpSeF2A86hs9MMW+pdnial690BSVJ2Y4CTSel3O2XLV0u9yy2kV1LjkBrMb3bVSbYG3gqcQkpAI1XlceC/gfOBPxY+FknSIEaQ9hs4n5QEp/QvRkszy0LgQuA43I5XHcgZAHW6iaRd+U4kZRo0Fa1WZzlpkekFwMWkNSZSR3IAoG6yHunX3EmkDYnWWv3/XF1iKXA16YH/E+DRokcjZeIAQN1qLCkt68nA8cDaZQ9HmS0ErgQuIT30Z5c9HCk/BwBSGgy8EngFcBSwednDUZBZwGWkB/4VPL8ZktSVHABILzadtIjwCNKAYN2yh6NhWghcT3rYXwH8gbS4TxIOAKSXsgZwAGkgcBSwO+krA9VPL3Ab8GtSXojrSFsgS1oFBwBSayaSdivcv7/sQ9qrQPnNB35PetDfCNwAzC16RFKDOACQ2jMS2JE0S7A/aXDgDnAxHgJuIk3r3wD8CVhW9IikBnMAIFVvEmlmYGdgJ9IAYQfcKGaolpISNUBIAAABRklEQVR2fryN9JC/jZR976mSByV1GgcAUh4jga1Ig4IdSQODnYEtgVEFj6ukRcB9wD39ZcVD/w5gScHjkrqCAwCprDVJnx1OHVCmDfjnFJo7QOgl5dCfBTwC3MvzD/t7gZn9/xtJBTgAkOptFGljo81Jg4GJwPqr+OfAf4+ynLTIbh5pK9wV/z4XeKy/zBzwz8fxHb1UWw4ApM7SA0wgbV6zIrvhWJ5ffzCh/38z8L8v4vmkOEuB5/r/vZf0gF9Cesiv+L9LkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiSpcv8Plam3e3w4bH8AAAAASUVORK5CYII=";
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        FamilyProfileDAO fpDAO = new FamilyProfileDAO();
        HttpSession session = request.getSession();
        List<FamilyProfile> fpList;
        UserDAO uDAO = new UserDAO();

        String id;

        if (request.getParameter("id") == null || request.getParameter("id").isEmpty()) {
            id = String.valueOf(1);
        } else {
            id = String.valueOf(request.getParameter("id"));
        }
        System.out.println(id);

        RelationshipDAO rDAO = new RelationshipDAO();
        ArrayList<Relationship> rList = rDAO.getRelationshipList();

        String ownerId = uDAO.getIdByEmail(String.valueOf(session.getAttribute("email")));
        fpList = fpDAO.getFamilyProfileListByUserOwnerId(ownerId);
        if (session.getAttribute("email") == null) {
            response.sendRedirect("user-login");
        } else {
            if (!fpList.isEmpty()) {
                request.setAttribute("fpList", fpList);
                request.setAttribute("currentfp", fpList.get(Integer.parseInt(id) - 1));
            }
            request.setAttribute("rList", rList);
            request.getRequestDispatcher("user-profile.jsp").forward(request, response);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        FamilyProfileDAO fpDAO = new FamilyProfileDAO();
        HttpSession session = request.getSession();
        List<FamilyProfile> fpList;
        UserDAO uDAO = new UserDAO();
        User u;

        String search;
        search = request.getParameter("search-profile");

        String ownerId = uDAO.getIdByEmail(String.valueOf(session.getAttribute("email")));

        RelationshipDAO rDAO = new RelationshipDAO();
        ArrayList<Relationship> rList = rDAO.getRelationshipList();

        fpList = fpDAO.getFamilyProfileListByUserOwnerId(ownerId);

        if (session.getAttribute("email") == null) {
            response.sendRedirect("user-login");
        } else {
            String name = request.getParameter("name");
            String phone = request.getParameter("phone");
            String birthDate = request.getParameter("birthDate");
            String gender = request.getParameter("gender");
            String medicalId = request.getParameter("medicalId");
            String identity = request.getParameter("identity");
            String address = request.getParameter("address");
            String ethnic = request.getParameter("ethnic");
            String email = request.getParameter("email");
            String imageFileName = "";

            //add photo
            String method = request.getParameter("method");
            switch (method) {
                case "search":
                    fpList = fpDAO.getFamilyProfileListByUserName(search, ownerId);
                    request.setAttribute("fpList", fpList);
                    request.setAttribute("rList", rList);
                    request.getRequestDispatcher("user-profile.jsp").forward(request, response);
                    break;
                case "add":
                    Part filePart;
                    filePart = request.getPart("photo");
                    boolean fileCheck = Base64Encoding.isFileValid(filePart);
                    if (filePart.getSubmittedFileName().equals("")) {
                        imageFileName = "data:image/png;base64, " + DEFAULT_PIC;
                    } else if (fileCheck == true) {
                        imageFileName = Base64Encoding.convertImageToBase64(filePart);
                    } else {
                        response.sendRedirect("user-profile");
                    }
                    System.out.println("method: " + method);
                    System.out.println("name: " + name);

                    LocalDate date = LocalDate.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    String currentDate = date.format(formatter);
                    String relationId = request.getParameter("relation");
                    FamilyProfile fp = new FamilyProfile(email, name, birthDate, gender, address, identity, medicalId, ethnic, phone, imageFileName, currentDate, relationId, ownerId);
                    System.out.println(fp.toString());
                    fpDAO.addNewUserProfile(fp);
                    request.setAttribute("fpList", fpList);
                    request.setAttribute("rList", rList);
                    response.sendRedirect("user-profile");
                    break;
                case "delete":
                    String profileId = request.getParameter("profileId");
                    fpDAO.deleteFamilyProfileByID(profileId);
                    request.setAttribute("fpList", fpList);
                    request.setAttribute("rList", rList);
                    response.sendRedirect("user-profile");
                    break;
                case "edit":
                    filePart = request.getPart("photo");
                    fileCheck = Base64Encoding.isFileValid(filePart);
                    if (filePart.getSubmittedFileName().equals("")) {
                        imageFileName = "data:image/png;base64, " + DEFAULT_PIC;
                    } else if (fileCheck == true) {
                        imageFileName = Base64Encoding.convertImageToBase64(filePart);
                    } else {
                        response.sendRedirect("user-profile");
                    }
                    System.out.println("method: " + method);
                    System.out.println("name: " + name);
                    profileId = request.getParameter("profileId");
                    relationId = request.getParameter("relation");
                    fp = new FamilyProfile();
                    fp.setProfileId(profileId);
                    fp.setEmail(email);
                    fp.setName(name);
                    fp.setBirthDate(birthDate);
                    fp.setGender(gender);
                    fp.setAddress(address);
                    fp.setIdentity(identity);
                    fp.setMedicalId(medicalId);
                    fp.setEthnic(ethnic);
                    fp.setPhone(phone);
                    fp.setProfilePicture(imageFileName);
                    fp.setRelationId(relationId);
                    fp.setOwnerId(ownerId);
                    System.out.println(fp.toString());
                    fpDAO.editFamilyProfileById(fp);
                    System.out.println(fp.getRelationId());
                    if (fp.getRelationId() == null || fp.getRelationId().isEmpty()) {
                        uDAO.editUserByFp(fp);
                        session.setAttribute("name", fp.getName());
                    }
                    request.setAttribute("fpList", fpList);
                    request.setAttribute("rList", rList);
                    response.sendRedirect("user-profile");
                    break;
                default:
                    throw new AssertionError();
            }

        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
