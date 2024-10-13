package com.gloit.tukohama.mail;

public class MailHelper {

    public static String verificarMail(String nombre, String token){
        String email="<body>\n" +
                "    <div class=\"es-wrapper-color\">\n" +
                "        <!--[if gte mso 9]>\n" +
                "\t\t\t<v:background xmlns:v=\"urn:schemas-microsoft-com:vml\" fill=\"t\">\n" +
                "\t\t\t\t<v:fill type=\"tile\" color=\"#fafafa\"></v:fill>\n" +
                "\t\t\t</v:background>\n" +
                "\t\t<![endif]-->\n" +
                "        <table class=\"es-wrapper\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "            <tbody>\n" +
                "                <tr>\n" +
                "                    <td class=\"esd-email-paddings\" valign=\"top\">\n" +
                "                        <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-content esd-header-popover\" align=\"center\">\n" +
                "                            <tbody>\n" +
                "                                <tr>\n" +
                "                                    <td class=\"es-adaptive esd-stripe\" align=\"center\">\n" +
                "                                        <table class=\"es-content-body\" style=\"background-color: transparent;\" width=\"600\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#ffffff\" align=\"center\">\n" +
                "                                            <tbody>\n" +
                "                                                <tr>\n" +
                "                                                    <td class=\"esd-structure es-p10\" align=\"left\">\n" +
                "                                                        <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "                                                            <tbody>\n" +
                "                                                                <tr>\n" +
                "                                                                    <td class=\"esd-container-frame\" width=\"580\" valign=\"top\" align=\"center\">\n" +
                "                                                                        <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "                                                                            <tbody>\n" +
                "                                                                                <tr>\n" +
                "                                                                                    <td align=\"center\" class=\"es-infoblock esd-block-text\">\n" +
                "                                                                                        <p>Put your preheader text here. <a href=\"https://viewstripo.email\" class=\"view\" target=\"_blank\">View in browser</a></p>\n" +
                "                                                                                    </td>\n" +
                "                                                                                </tr>\n" +
                "                                                                            </tbody>\n" +
                "                                                                        </table>\n" +
                "                                                                    </td>\n" +
                "                                                                </tr>\n" +
                "                                                            </tbody>\n" +
                "                                                        </table>\n" +
                "                                                    </td>\n" +
                "                                                </tr>\n" +
                "                                                <tr>\n" +
                "                                                    <td class=\"esd-structure es-p20t es-p20r es-p20l\" align=\"left\">\n" +
                "                                                        <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "                                                            <tbody>\n" +
                "                                                                <tr>\n" +
                "                                                                    <td width=\"560\" class=\"esd-container-frame\" align=\"center\" valign=\"top\">\n" +
                "                                                                        <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "                                                                            <tbody>\n" +
                "                                                                                <tr>\n" +
                "                                                                                    <td align=\"center\" class=\"esd-block-image\" style=\"font-size: 0px;\"><a target=\"_blank\"><img class=\"adapt-img\" src=\"https://nxzoik.stripocdn.email/content/guids/CABINET_8fb850e57130956d9b5c4fb10db46d35/images/83691615677909421.png\" alt style=\"display: block;\" width=\"560\"></a></td>\n" +
                "                                                                                </tr>\n" +
                "                                                                            </tbody>\n" +
                "                                                                        </table>\n" +
                "                                                                    </td>\n" +
                "                                                                </tr>\n" +
                "                                                            </tbody>\n" +
                "                                                        </table>\n" +
                "                                                    </td>\n" +
                "                                                </tr>\n" +
                "                                            </tbody>\n" +
                "                                        </table>\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                            </tbody>\n" +
                "                        </table>\n" +
                "                        <table class=\"es-content\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">\n" +
                "                            <tbody>\n" +
                "                                <tr>\n" +
                "                                    <td class=\"esd-stripe\" style=\"background-color: #fafafa;\" bgcolor=\"#fafafa\" align=\"center\">\n" +
                "                                        <table class=\"es-content-body\" style=\"background-color: #ffffff;\" width=\"600\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#ffffff\" align=\"center\">\n" +
                "                                            <tbody>\n" +
                "                                                <tr>\n" +
                "                                                    <td class=\"esd-structure es-p40t es-p20r es-p20l\" style=\"background-color: transparent;\" bgcolor=\"transparent\" align=\"left\">\n" +
                "                                                        <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "                                                            <tbody>\n" +
                "                                                                <tr>\n" +
                "                                                                    <td class=\"esd-container-frame\" width=\"560\" valign=\"top\" align=\"center\">\n" +
                "                                                                        <table style=\"background-position: left top;\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "                                                                            <tbody>\n" +
                "                                                                                <tr>\n" +
                "                                                                                    <td class=\"esd-block-image es-p5t es-p5b\" align=\"center\" style=\"font-size: 0px;\"><a target=\"_blank\"><img src=\"https://nxzoik.stripocdn.email/content/guids/CABINET_8fb850e57130956d9b5c4fb10db46d35/images/90971615678255138.png\" alt style=\"display: block;\" width=\"175\"></a></td>\n" +
                "                                                                                </tr>\n" +
                "                                                                                <tr>\n" +
                "                                                                                    <td class=\"esd-block-text es-p15t es-p15b\" align=\"center\">\n" +
                "                                                                                        <h1 style=\"color: #333333; font-size: 20px;\"><b>¡Estás a un paso de finalizar tu registro!</b></h1>\n" +
                "                                                                                    </td>\n" +
                "                                                                                </tr>\n" +
                "                                                                                <tr>\n" +
                "                                                                                    <td class=\"esd-block-text es-p40r es-p40l\" align=\"center\">\n" +
                "                                                                                        <p>Hola "+nombre+ ", tu cuenta en la tienda de Lopez y Orsini mail esta lista para ser activada. Solo debes verificar tu email.</p>\n" +
                "                                                                                    </td>\n" +
                "                                                                                </tr>\n" +
                "                                                                                <tr>\n" +
                "                                                                                    <td class=\"esd-block-text es-p35r es-p40l\" align=\"center\">\n" +
                "                                                                                        <p><br></p>\n" +
                "                                                                                    </td>\n" +
                "                                                                                </tr>\n" +
                "                                                                                <tr>\n" +
                "                                                                                    <td class=\"esd-block-button es-p40t es-p40b es-p10r es-p10l\" align=\"center\"><span class=\"es-button-border es-button-border-1615678352855\" style=\"background: #f2f4f6; border-color: #e84640;\"><a href=\""+token+"\" class=\"es-button es-button-1615678367267\" target=\"_blank\" style=\"color: #e84640; background: #f2f4f6; border-color: #f2f4f6;\">Verificar email</a></span></td>\n" +
                "                                                                                </tr>\n" +
                "                                                                                <tr>\n" +
                "                                                                                    <td class=\"esd-block-text es-p25t es-p40r es-p40l\" align=\"center\">\n" +
                "                                                                                        <p>\n\nSi no puedes hacer click en el botón copia y pega el siguiente link en tu navegador:</p>\n" +
                "                                                                                        <p>"+token+"</p>" +
                "                                                                                    </td>\n" +
                "                                                                                </tr>\n" +
                "                                                                            </tbody>\n" +
                "                                                                        </table>\n" +
                "                                                                    </td>\n" +
                "                                                                </tr>\n" +
                "                                                            </tbody>\n" +
                "                                                        </table>\n" +
                "                                                    </td>\n" +
                "                                                </tr>\n" +
                "                                                <tr>\n" +
                "                                                    <td class=\"esd-structure es-p20t es-p10r es-p10l\" style=\"background-position: center center;\" align=\"left\">\n" +
                "                                                        <!--[if mso]><table width=\"580\" cellpadding=\"0\" cellspacing=\"0\"><tr><td width=\"199\" valign=\"top\"><![endif]-->\n" +
                "                                                        <table class=\"es-left\" cellspacing=\"0\" cellpadding=\"0\" align=\"left\">\n" +
                "                                                            <tbody>\n" +
                "                                                                <tr>\n" +
                "                                                                    <td class=\"esd-container-frame\" width=\"199\" align=\"left\">\n" +
                "                                                                        <table style=\"background-position: center center;\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "                                                                            <tbody>\n" +
                "                                                                                <tr>\n" +
                "                                                                                    <td class=\"esd-block-text es-p15t es-m-txt-c\" align=\"right\">\n" +
                "                                                                                        <p style=\"font-size: 16px; color: #666666;\"><strong>Seguinos:</strong></p>\n" +
                "                                                                                    </td>\n" +
                "                                                                                </tr>\n" +
                "                                                                            </tbody>\n" +
                "                                                                        </table>\n" +
                "                                                                    </td>\n" +
                "                                                                </tr>\n" +
                "                                                            </tbody>\n" +
                "                                                        </table>\n" +
                "                                                        <!--[if mso]></td><td width=\"20\"></td><td width=\"361\" valign=\"top\"><![endif]-->\n" +
                "                                                        <table class=\"es-right\" cellspacing=\"0\" cellpadding=\"0\" align=\"right\">\n" +
                "                                                            <tbody>\n" +
                "                                                                <tr>\n" +
                "                                                                    <td class=\"esd-container-frame\" width=\"361\" align=\"left\">\n" +
                "                                                                        <table style=\"background-position: center center;\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "                                                                            <tbody>\n" +
                "                                                                                <tr>\n" +
                "                                                                                    <td class=\"esd-block-social es-p10t es-p5b es-m-txt-c\" align=\"left\" style=\"font-size:0\">\n" +
                "                                                                                        <table class=\"es-table-not-adapt es-social\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "                                                                                            <tbody>\n" +
                "                                                                                                <tr>\n" +
                "                                                                                                    <td class=\"es-p10r\" valign=\"top\" align=\"center\"><a target=\"_blank\" href=\"https://www.facebook.com/lopezyorsini\"><img src=\"https://nxzoik.stripocdn.email/content/assets/img/social-icons/circle-colored/facebook-circle-colored.png\" alt=\"Fb\" title=\"Facebook\" width=\"32\"></a></td>\n" +
                "                                                                                                    <td class=\"es-p10r\" valign=\"top\" align=\"center\"><a target=\"_blank\" href=\"https://www.instagram.com/lyoestudio/?hl=es-la\"><img src=\"https://nxzoik.stripocdn.email/content/assets/img/social-icons/circle-colored/instagram-circle-colored.png\" alt=\"Ig\" title=\"Instagram\" width=\"32\"></a></td>\n" +
                "                                                                                                    <td class=\"es-p10r\" valign=\"top\" align=\"center\" esd-tmp-icon-type=\"whatsapp\"><a target=\"_blank\" href=\"https://api.whatsapp.com/send?phone=543435009187&fbclid=IwAR0t1aEshsu0J9uwAr3DiTQBdwBAJDUlimLQv2awpUt54z-oYaeYavsOy1A\"><img src=\"https://nxzoik.stripocdn.email/content/assets/img/messenger-icons/circle-colored/whatsapp-circle-colored.png\" alt=\"Whatsapp\" title=\"Whatsapp\" width=\"32\"></a></td>\n" +
                "                                                                                                    <td class=\"es-p10r\" valign=\"top\" align=\"center\" esd-tmp-icon-type=\"world\"><a target=\"_blank\" href=\"https://lyoestudio.com.ar\"><img src=\"https://nxzoik.stripocdn.email/content/assets/img/other-icons/circle-colored/globe-circle-colored.png\" alt=\"World\" title=\"World\" width=\"32\"></a></td>\n" +
                "                                                                                                    <td valign=\"top\" align=\"center\" esd-tmp-icon-type=\"email\"><a target=\"_blank\" href=\"mailto:info@lyoestudio.com.ar\"><img src=\"https://nxzoik.stripocdn.email/content/assets/img/other-icons/circle-colored/mail-circle-colored.png\" alt=\"Email\" title=\"Email\" width=\"32\"></a></td>\n" +
                "                                                                                                </tr>\n" +
                "                                                                                            </tbody>\n" +
                "                                                                                        </table>\n" +
                "                                                                                    </td>\n" +
                "                                                                                </tr>\n" +
                "                                                                            </tbody>\n" +
                "                                                                        </table>\n" +
                "                                                                    </td>\n" +
                "                                                                </tr>\n" +
                "                                                            </tbody>\n" +
                "                                                        </table>\n" +
                "                                                        <!--[if mso]></td></tr></table><![endif]-->\n" +
                "                                                    </td>\n" +
                "                                                </tr>\n" +
                "                                                <tr>\n" +
                "                                                    <td class=\"esd-structure es-p5t es-p20b es-p20r es-p20l\" style=\"background-position: left top;\" align=\"left\">\n" +
                "                                                        <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "                                                            <tbody>\n" +
                "                                                                <tr>\n" +
                "                                                                    <td class=\"esd-container-frame\" width=\"560\" valign=\"top\" align=\"center\">\n" +
                "                                                                        <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "                                                                            <tbody>\n" +
                "                                                                                <tr>\n" +
                "                                                                                    <td class=\"esd-block-text\" esd-links-color=\"#666666\" align=\"center\">\n" +
                "                                                                                        <p style=\"font-size: 14px;\">Contactanos: 0343 4954735 | 0343 5272040 | 343 5009187</p>\n" +
                "                                                                                    </td>\n" +
                "                                                                                </tr>\n" +
                "                                                                            </tbody>\n" +
                "                                                                        </table>\n" +
                "                                                                    </td>\n" +
                "                                                                </tr>\n" +
                "                                                            </tbody>\n" +
                "                                                        </table>\n" +
                "                                                    </td>\n" +
                "                                                </tr>\n" +
                "                                            </tbody>\n" +
                "                                        </table>\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                            </tbody>\n" +
                "                        </table>\n" +
                "                        <table class=\"es-footer\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">\n" +
                "                            <tbody>\n" +
                "                                <tr>\n" +
                "                                    <td class=\"esd-stripe\" style=\"background-color: #fafafa;\" bgcolor=\"#fafafa\" align=\"center\">\n" +
                "                                        <table class=\"es-footer-body\" width=\"600\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#ffffff\" align=\"center\">\n" +
                "                                            <tbody>\n" +
                "                                                <tr>\n" +
                "                                                    <td class=\"esd-structure es-p10t es-p30b es-p20r es-p20l\" style=\"background-color: #10556b;\" bgcolor=\"#10556B\" align=\"left\">\n" +
                "                                                        <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "                                                            <tbody>\n" +
                "                                                                <tr>\n" +
                "                                                                    <td class=\"esd-container-frame\" width=\"560\" valign=\"top\" align=\"center\">\n" +
                "                                                                        <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "                                                                            <tbody>\n" +
                "                                                                                <tr>\n" +
                "                                                                                    <td class=\"esd-block-text es-p5t es-p5b\" align=\"left\">\n" +
                "                                                                                        <h2 style=\"font-size: 16px; color: #ffffff;\"><strong>¿Tenés preguntas o dudas?</strong></h2>\n" +
                "                                                                                    </td>\n" +
                "                                                                                </tr>\n" +
                "                                                                                <tr>\n" +
                "                                                                                    <td esd-links-underline=\"none\" esd-links-color=\"#ffffff\" class=\"esd-block-text es-p5b\" align=\"left\">\n" +
                "                                                                                        <p style=\"font-size: 14px; color: #ffffff;\">Si tenes cualquier duda sobre la tienda o tecnológica, no dudes en contactarte con nosotros.</p>\n" +
                "                                                                                    </td>\n" +
                "                                                                                </tr>\n" +
                "                                                                            </tbody>\n" +
                "                                                                        </table>\n" +
                "                                                                    </td>\n" +
                "                                                                </tr>\n" +
                "                                                            </tbody>\n" +
                "                                                        </table>\n" +
                "                                                    </td>\n" +
                "                                                </tr>\n" +
                "                                            </tbody>\n" +
                "                                        </table>\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                            </tbody>\n" +
                "                        </table>\n" +
                "                        <table class=\"es-content\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">\n" +
                "                            <tbody>\n" +
                "                                <tr>\n" +
                "                                    <td class=\"esd-stripe\" style=\"background-color: #fafafa;\" bgcolor=\"#fafafa\" align=\"center\">\n" +
                "                                        <table class=\"es-content-body\" style=\"background-color: transparent;\" width=\"600\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"transparent\" align=\"center\">\n" +
                "                                            <tbody>\n" +
                "                                                <tr>\n" +
                "                                                    <td class=\"esd-structure es-p15t\" style=\"background-position: left top;\" align=\"left\">\n" +
                "                                                        <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "                                                            <tbody>\n" +
                "                                                                <tr>\n" +
                "                                                                    <td class=\"esd-container-frame\" width=\"600\" valign=\"top\" align=\"center\">\n" +
                "                                                                        <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "                                                                            <tbody>\n" +
                "                                                                                <tr>\n" +
                "                                                                                    <td class=\"esd-block-menu\">\n" +
                "                                                                                        <table class=\"es-menu\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "                                                                                            <tbody>\n" +
                "                                                                                                <tr class=\"links\">\n" +
                "                                                                                                    <td class=\"es-p10t es-p10b es-p5r es-p5l\" style=\"padding-bottom: 1px; padding-top: 0px; \" width=\"33.33%\" valign=\"top\" bgcolor=\"transparent\" align=\"center\"><a target=\"_blank\" href=\"https://lyoestudio.com.ar\" style=\"color: #3D5CA3; font-size: 14px;\">L&O Estudio</a></td>\n" +
                "                                                                                                    <td class=\"es-p10t es-p10b es-p5r es-p5l\" style=\"border-left: 1px solid #3d5ca3; padding-bottom: 1px; padding-top: 0px; \" width=\"33.33%\" valign=\"top\" bgcolor=\"transparent\" align=\"center\"><a target=\"_blank\" href=\"https://lyoestudio.com.ar/tienda\" style=\"color: #3D5CA3; font-size: 14px;\">Tienda</a></td>\n" +
                "                                                                                                    <td class=\"es-p10t es-p10b es-p5r es-p5l\" style=\"border-left: 1px solid #3d5ca3; padding-bottom: 1px; padding-top: 0px; \" width=\"33.33%\" valign=\"top\" bgcolor=\"transparent\" align=\"center\"><a target=\"_blank\" href=\"https://lyoestudio.com.ar/contaco\" style=\"color: #3D5CA3; font-size: 14px;\">Contacto</a></td>\n" +
                "                                                                                                </tr>\n" +
                "                                                                                            </tbody>\n" +
                "                                                                                        </table>\n" +
                "                                                                                    </td>\n" +
                "                                                                                </tr>\n" +
                "                                                                                <tr>\n" +
                "                                                                                    <td class=\"esd-block-spacer es-p20b es-p20r es-p20l\" align=\"center\" style=\"font-size:0\">\n" +
                "                                                                                        <table width=\"100%\" height=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\">\n" +
                "                                                                                            <tbody>\n" +
                "                                                                                                <tr>\n" +
                "                                                                                                    <td style=\"border-bottom: 1px solid #fafafa; background: none; height: 1px; width: 100%; margin: 0px;\"></td>\n" +
                "                                                                                                </tr>\n" +
                "                                                                                            </tbody>\n" +
                "                                                                                        </table>\n" +
                "                                                                                    </td>\n" +
                "                                                                                </tr>\n" +
                "                                                                            </tbody>\n" +
                "                                                                        </table>\n" +
                "                                                                    </td>\n" +
                "                                                                </tr>\n" +
                "                                                            </tbody>\n" +
                "                                                        </table>\n" +
                "                                                    </td>\n" +
                "                                                </tr>\n" +
                "                                            </tbody>\n" +
                "                                        </table>\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                            </tbody>\n" +
                "                        </table>\n" +
                "                        <table class=\"es-footer\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">\n" +
                "                            <tbody>\n" +
                "                                <tr>\n" +
                "                                    <td class=\"esd-stripe\" style=\"background-color: #fafafa;\" bgcolor=\"#fafafa\" align=\"center\">\n" +
                "                                        <table class=\"es-footer-body\" style=\"background-color: transparent;\" width=\"600\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"transparent\" align=\"center\">\n" +
                "                                            <tbody>\n" +
                "                                                <tr>\n" +
                "                                                    <td class=\"esd-structure es-p15t es-p5b es-p20r es-p20l\" align=\"left\">\n" +
                "                                                        <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "                                                            <tbody>\n" +
                "                                                                <tr>\n" +
                "                                                                    <td class=\"esd-container-frame\" width=\"560\" valign=\"top\" align=\"center\">\n" +
                "                                                                        <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "                                                                            <tbody>\n" +
                "                                                                                <tr>\n" +
                "                                                                                    <td align=\"center\" class=\"esd-empty-container\" style=\"display: none;\"></td>\n" +
                "                                                                                </tr>\n" +
                "                                                                            </tbody>\n" +
                "                                                                        </table>\n" +
                "                                                                    </td>\n" +
                "                                                                </tr>\n" +
                "                                                            </tbody>\n" +
                "                                                        </table>\n" +
                "                                                    </td>\n" +
                "                                                </tr>\n" +
                "                                            </tbody>\n" +
                "                                        </table>\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                            </tbody>\n" +
                "                        </table>\n" +
                "                        <table class=\"es-content esd-footer-popover\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">\n" +
                "                            <tbody>\n" +
                "                                <tr>\n" +
                "                                    <td class=\"esd-stripe\" align=\"center\">\n" +
                "                                        <table class=\"es-content-body\" style=\"background-color: transparent;\" width=\"600\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#ffffff\" align=\"center\">\n" +
                "                                            <tbody>\n" +
                "                                                <tr>\n" +
                "                                                    <td class=\"esd-structure es-p30t es-p30b es-p20r es-p20l\" align=\"left\">\n" +
                "                                                        <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "                                                            <tbody>\n" +
                "                                                                <tr>\n" +
                "                                                                    <td class=\"esd-container-frame\" width=\"560\" valign=\"top\" align=\"center\">\n" +
                "                                                                        <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "                                                                            <tbody>\n" +
                "                                                                                <tr>\n" +
                "                                                                                    <td align=\"center\" class=\"esd-empty-container\" style=\"display: none;\"></td>\n" +
                "                                                                                </tr>\n" +
                "                                                                            </tbody>\n" +
                "                                                                        </table>\n" +
                "                                                                    </td>\n" +
                "                                                                </tr>\n" +
                "                                                            </tbody>\n" +
                "                                                        </table>\n" +
                "                                                    </td>\n" +
                "                                                </tr>\n" +
                "                                            </tbody>\n" +
                "                                        </table>\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                            </tbody>\n" +
                "                        </table>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "            </tbody>\n" +
                "        </table>\n" +
                "    </div>\n" +
                "</body>"+
                "<style>" +
                "#outlook a {\n" +
                "    padding: 0;\n" +
                "}\n" +
                "\n" +
                ".ExternalClass {\n" +
                "    width: 100%;\n" +
                "}\n" +
                "\n" +
                ".ExternalClass,\n" +
                ".ExternalClass p,\n" +
                ".ExternalClass span,\n" +
                ".ExternalClass font,\n" +
                ".ExternalClass td,\n" +
                ".ExternalClass div {\n" +
                "    line-height: 100%;\n" +
                "}\n" +
                "\n" +
                ".es-button {\n" +
                "    mso-style-priority: 100 !important;\n" +
                "    text-decoration: none !important;\n" +
                "}\n" +
                "\n" +
                "a[x-apple-data-detectors] {\n" +
                "    color: inherit !important;\n" +
                "    text-decoration: none !important;\n" +
                "    font-size: inherit !important;\n" +
                "    font-family: inherit !important;\n" +
                "    font-weight: inherit !important;\n" +
                "    line-height: inherit !important;\n" +
                "}\n" +
                "\n" +
                ".es-desk-hidden {\n" +
                "    display: none;\n" +
                "    float: left;\n" +
                "    overflow: hidden;\n" +
                "    width: 0;\n" +
                "    max-height: 0;\n" +
                "    line-height: 0;\n" +
                "    mso-hide: all;\n" +
                "}\n" +
                "\n" +
                ".es-button-border:hover a.es-button,\n" +
                ".es-button-border:hover button.es-button {\n" +
                "    background: #ffffff !important;\n" +
                "    border-color: #ffffff !important;\n" +
                "}\n" +
                "\n" +
                ".es-button-border:hover {\n" +
                "    background: #ffffff !important;\n" +
                "    border-style: solid solid solid solid !important;\n" +
                "    border-color: #3d5ca3 #3d5ca3 #3d5ca3 #3d5ca3 !important;\n" +
                "}\n" +
                "\n" +
                "td .es-button-border-1615678352855:hover {\n" +
                "    border-color: #db2019 #db2019 #db2019 #db2019 !important;\n" +
                "    background: #d4dbe1 !important;\n" +
                "}\n" +
                "\n" +
                "td .es-button-border:hover a.es-button-1615678367267 {\n" +
                "    background: #d4dbe1 !important;\n" +
                "    border-color: #d4dbe1 !important;\n" +
                "}\n" +
                "\n" +
                "/*\n" +
                "END OF IMPORTANT\n" +
                "*/\n" +
                "s {\n" +
                "    text-decoration: line-through;\n" +
                "}\n" +
                "\n" +
                "html,\n" +
                "body {\n" +
                "    width: 100%;\n" +
                "    font-family: helvetica, 'helvetica neue', arial, verdana, sans-serif;\n" +
                "    -webkit-text-size-adjust: 100%;\n" +
                "    -ms-text-size-adjust: 100%;\n" +
                "}\n" +
                "\n" +
                "table {\n" +
                "    mso-table-lspace: 0pt;\n" +
                "    mso-table-rspace: 0pt;\n" +
                "    border-collapse: collapse;\n" +
                "    border-spacing: 0px;\n" +
                "}\n" +
                "\n" +
                "table td,\n" +
                "html,\n" +
                "body,\n" +
                ".es-wrapper {\n" +
                "    padding: 0;\n" +
                "    Margin: 0;\n" +
                "}\n" +
                "\n" +
                ".es-content,\n" +
                ".es-header,\n" +
                ".es-footer {\n" +
                "    table-layout: fixed !important;\n" +
                "    width: 100%;\n" +
                "}\n" +
                "\n" +
                "img {\n" +
                "    display: block;\n" +
                "    border: 0;\n" +
                "    outline: none;\n" +
                "    text-decoration: none;\n" +
                "    -ms-interpolation-mode: bicubic;\n" +
                "}\n" +
                "\n" +
                "table tr {\n" +
                "    border-collapse: collapse;\n" +
                "}\n" +
                "\n" +
                "p,\n" +
                "hr {\n" +
                "    Margin: 0;\n" +
                "}\n" +
                "\n" +
                "h1,\n" +
                "h2,\n" +
                "h3,\n" +
                "h4,\n" +
                "h5 {\n" +
                "    Margin: 0;\n" +
                "    line-height: 120%;\n" +
                "    mso-line-height-rule: exactly;\n" +
                "    font-family: arial, 'helvetica neue', helvetica, sans-serif;\n" +
                "}\n" +
                "\n" +
                "p,\n" +
                "ul li,\n" +
                "ol li,\n" +
                "a {\n" +
                "    -webkit-text-size-adjust: none;\n" +
                "    -ms-text-size-adjust: none;\n" +
                "    mso-line-height-rule: exactly;\n" +
                "}\n" +
                "\n" +
                ".es-left {\n" +
                "    float: left;\n" +
                "}\n" +
                "\n" +
                ".es-right {\n" +
                "    float: right;\n" +
                "}\n" +
                "\n" +
                ".es-p5 {\n" +
                "    padding: 5px;\n" +
                "}\n" +
                "\n" +
                ".es-p5t {\n" +
                "    padding-top: 5px;\n" +
                "}\n" +
                "\n" +
                ".es-p5b {\n" +
                "    padding-bottom: 5px;\n" +
                "}\n" +
                "\n" +
                ".es-p5l {\n" +
                "    padding-left: 5px;\n" +
                "}\n" +
                "\n" +
                ".es-p5r {\n" +
                "    padding-right: 5px;\n" +
                "}\n" +
                "\n" +
                ".es-p10 {\n" +
                "    padding: 10px;\n" +
                "}\n" +
                "\n" +
                ".es-p10t {\n" +
                "    padding-top: 10px;\n" +
                "}\n" +
                "\n" +
                ".es-p10b {\n" +
                "    padding-bottom: 10px;\n" +
                "}\n" +
                "\n" +
                ".es-p10l {\n" +
                "    padding-left: 10px;\n" +
                "}\n" +
                "\n" +
                ".es-p10r {\n" +
                "    padding-right: 10px;\n" +
                "}\n" +
                "\n" +
                ".es-p15 {\n" +
                "    padding: 15px;\n" +
                "}\n" +
                "\n" +
                ".es-p15t {\n" +
                "    padding-top: 15px;\n" +
                "}\n" +
                "\n" +
                ".es-p15b {\n" +
                "    padding-bottom: 15px;\n" +
                "}\n" +
                "\n" +
                ".es-p15l {\n" +
                "    padding-left: 15px;\n" +
                "}\n" +
                "\n" +
                ".es-p15r {\n" +
                "    padding-right: 15px;\n" +
                "}\n" +
                "\n" +
                ".es-p20 {\n" +
                "    padding: 20px;\n" +
                "}\n" +
                "\n" +
                ".es-p20t {\n" +
                "    padding-top: 20px;\n" +
                "}\n" +
                "\n" +
                ".es-p20b {\n" +
                "    padding-bottom: 20px;\n" +
                "}\n" +
                "\n" +
                ".es-p20l {\n" +
                "    padding-left: 20px;\n" +
                "}\n" +
                "\n" +
                ".es-p20r {\n" +
                "    padding-right: 20px;\n" +
                "}\n" +
                "\n" +
                ".es-p25 {\n" +
                "    padding: 25px;\n" +
                "}\n" +
                "\n" +
                ".es-p25t {\n" +
                "    padding-top: 25px;\n" +
                "}\n" +
                "\n" +
                ".es-p25b {\n" +
                "    padding-bottom: 25px;\n" +
                "}\n" +
                "\n" +
                ".es-p25l {\n" +
                "    padding-left: 25px;\n" +
                "}\n" +
                "\n" +
                ".es-p25r {\n" +
                "    padding-right: 25px;\n" +
                "}\n" +
                "\n" +
                ".es-p30 {\n" +
                "    padding: 30px;\n" +
                "}\n" +
                "\n" +
                ".es-p30t {\n" +
                "    padding-top: 30px;\n" +
                "}\n" +
                "\n" +
                ".es-p30b {\n" +
                "    padding-bottom: 30px;\n" +
                "}\n" +
                "\n" +
                ".es-p30l {\n" +
                "    padding-left: 30px;\n" +
                "}\n" +
                "\n" +
                ".es-p30r {\n" +
                "    padding-right: 30px;\n" +
                "}\n" +
                "\n" +
                ".es-p35 {\n" +
                "    padding: 35px;\n" +
                "}\n" +
                "\n" +
                ".es-p35t {\n" +
                "    padding-top: 35px;\n" +
                "}\n" +
                "\n" +
                ".es-p35b {\n" +
                "    padding-bottom: 35px;\n" +
                "}\n" +
                "\n" +
                ".es-p35l {\n" +
                "    padding-left: 35px;\n" +
                "}\n" +
                "\n" +
                ".es-p35r {\n" +
                "    padding-right: 35px;\n" +
                "}\n" +
                "\n" +
                ".es-p40 {\n" +
                "    padding: 40px;\n" +
                "}\n" +
                "\n" +
                ".es-p40t {\n" +
                "    padding-top: 40px;\n" +
                "}\n" +
                "\n" +
                ".es-p40b {\n" +
                "    padding-bottom: 40px;\n" +
                "}\n" +
                "\n" +
                ".es-p40l {\n" +
                "    padding-left: 40px;\n" +
                "}\n" +
                "\n" +
                ".es-p40r {\n" +
                "    padding-right: 40px;\n" +
                "}\n" +
                "\n" +
                ".es-menu td {\n" +
                "    border: 0;\n" +
                "}\n" +
                "\n" +
                ".es-menu td a img {\n" +
                "    display: inline-block !important;\n" +
                "}\n" +
                "\n" +
                "/* END CONFIG STYLES */\n" +
                "a {\n" +
                "    text-decoration: none;\n" +
                "}\n" +
                "\n" +
                "p,\n" +
                "ul li,\n" +
                "ol li {\n" +
                "    font-family: helvetica, 'helvetica neue', arial, verdana, sans-serif;\n" +
                "    line-height: 150%;\n" +
                "}\n" +
                "\n" +
                "ul li,\n" +
                "ol li {\n" +
                "    Margin-bottom: 15px;\n" +
                "}\n" +
                "\n" +
                ".es-menu td a {\n" +
                "    text-decoration: none;\n" +
                "    display: block;\n" +
                "}\n" +
                "\n" +
                ".es-wrapper {\n" +
                "    width: 100%;\n" +
                "    height: 100%;\n" +
                "    background-image: ;\n" +
                "    background-repeat: repeat;\n" +
                "    background-position: center top;\n" +
                "}\n" +
                "\n" +
                ".es-wrapper-color {\n" +
                "    background-color: #fafafa;\n" +
                "}\n" +
                "\n" +
                ".es-header {\n" +
                "    background-color: transparent;\n" +
                "    background-image: ;\n" +
                "    background-repeat: repeat;\n" +
                "    background-position: center top;\n" +
                "}\n" +
                "\n" +
                ".es-header-body {\n" +
                "    background-color: #ffffff;\n" +
                "}\n" +
                "\n" +
                ".es-header-body p,\n" +
                ".es-header-body ul li,\n" +
                ".es-header-body ol li {\n" +
                "    color: #333333;\n" +
                "    font-size: 14px;\n" +
                "}\n" +
                "\n" +
                ".es-header-body a {\n" +
                "    color: #1376c8;\n" +
                "    font-size: 14px;\n" +
                "}\n" +
                "\n" +
                ".es-content-body {\n" +
                "    background-color: #ffffff;\n" +
                "}\n" +
                "\n" +
                ".es-content-body p,\n" +
                ".es-content-body ul li,\n" +
                ".es-content-body ol li {\n" +
                "    color: #666666;\n" +
                "    font-size: 16px;\n" +
                "}\n" +
                "\n" +
                ".es-content-body a {\n" +
                "    color: #0b5394;\n" +
                "    font-size: 16px;\n" +
                "}\n" +
                "\n" +
                ".es-footer {\n" +
                "    background-color: transparent;\n" +
                "    background-image: ;\n" +
                "    background-repeat: repeat;\n" +
                "    background-position: center top;\n" +
                "}\n" +
                "\n" +
                ".es-footer-body {\n" +
                "    background-color: transparent;\n" +
                "}\n" +
                "\n" +
                ".es-footer-body p,\n" +
                ".es-footer-body ul li,\n" +
                ".es-footer-body ol li {\n" +
                "    color: #333333;\n" +
                "    font-size: 14px;\n" +
                "}\n" +
                "\n" +
                ".es-footer-body a {\n" +
                "    color: #333333;\n" +
                "    font-size: 14px;\n" +
                "}\n" +
                "\n" +
                ".es-infoblock,\n" +
                ".es-infoblock p,\n" +
                ".es-infoblock ul li,\n" +
                ".es-infoblock ol li {\n" +
                "    line-height: 120%;\n" +
                "    font-size: 12px;\n" +
                "    color: #cccccc;\n" +
                "}\n" +
                "\n" +
                ".es-infoblock a {\n" +
                "    font-size: 12px;\n" +
                "    color: #cccccc;\n" +
                "}\n" +
                "\n" +
                "h1 {\n" +
                "    font-size: 20px;\n" +
                "    font-style: normal;\n" +
                "    font-weight: normal;\n" +
                "    color: #333333;\n" +
                "}\n" +
                "\n" +
                "h2 {\n" +
                "    font-size: 14px;\n" +
                "    font-style: normal;\n" +
                "    font-weight: normal;\n" +
                "    color: #333333;\n" +
                "}\n" +
                "\n" +
                "h3 {\n" +
                "    font-size: 20px;\n" +
                "    font-style: normal;\n" +
                "    font-weight: normal;\n" +
                "    color: #333333;\n" +
                "}\n" +
                "\n" +
                ".es-header-body h1 a,\n" +
                ".es-content-body h1 a,\n" +
                ".es-footer-body h1 a {\n" +
                "    font-size: 20px;\n" +
                "}\n" +
                "\n" +
                ".es-header-body h2 a,\n" +
                ".es-content-body h2 a,\n" +
                ".es-footer-body h2 a {\n" +
                "    font-size: 14px;\n" +
                "}\n" +
                "\n" +
                ".es-header-body h3 a,\n" +
                ".es-content-body h3 a,\n" +
                ".es-footer-body h3 a {\n" +
                "    font-size: 20px;\n" +
                "}\n" +
                "\n" +
                "a.es-button,\n" +
                "button.es-button {\n" +
                "    border-style: solid;\n" +
                "    border-color: #ffffff;\n" +
                "    border-width: 15px 20px 15px 20px;\n" +
                "    display: inline-block;\n" +
                "    background: #ffffff;\n" +
                "    border-radius: 10px;\n" +
                "    font-size: 14px;\n" +
                "    font-family: arial, 'helvetica neue', helvetica, sans-serif;\n" +
                "    font-weight: bold;\n" +
                "    font-style: normal;\n" +
                "    line-height: 120%;\n" +
                "    color: #3D5CA3;\n" +
                "    text-decoration: none;\n" +
                "    width: auto;\n" +
                "    text-align: center;\n" +
                "}\n" +
                "\n" +
                ".es-button-border {\n" +
                "    border-style: solid solid solid solid;\n" +
                "    border-color: #3d5ca3 #3d5ca3 #3d5ca3 #3d5ca3;\n" +
                "    background: #ffffff;\n" +
                "    border-width: 2px 2px 2px 2px;\n" +
                "    display: inline-block;\n" +
                "    border-radius: 10px;\n" +
                "    width: auto;\n" +
                "}\n" +
                "\n" +
                "/* RESPONSIVE STYLES Please do not delete and edit CSS styles below. If you don't need responsive layout, please delete this section. */\n" +
                "@media only screen and (max-width: 600px) {\n" +
                "\n" +
                "    p,\n" +
                "    ul li,\n" +
                "    ol li,\n" +
                "    a {\n" +
                "        line-height: 150% !important;\n" +
                "    }\n" +
                "\n" +
                "    h1 {\n" +
                "        font-size: 20px !important;\n" +
                "        text-align: center;\n" +
                "        line-height: 120% !important;\n" +
                "    }\n" +
                "\n" +
                "    h2 {\n" +
                "        font-size: 16px !important;\n" +
                "        text-align: left;\n" +
                "        line-height: 120% !important;\n" +
                "    }\n" +
                "\n" +
                "    h3 {\n" +
                "        font-size: 20px !important;\n" +
                "        text-align: center;\n" +
                "        line-height: 120% !important;\n" +
                "    }\n" +
                "\n" +
                "    .es-header-body h1 a,\n" +
                "    .es-content-body h1 a,\n" +
                "    .es-footer-body h1 a {\n" +
                "        font-size: 20px !important;\n" +
                "    }\n" +
                "\n" +
                "    h2 a {\n" +
                "        text-align: left;\n" +
                "    }\n" +
                "\n" +
                "    .es-header-body h2 a,\n" +
                "    .es-content-body h2 a,\n" +
                "    .es-footer-body h2 a {\n" +
                "        font-size: 16px !important;\n" +
                "    }\n" +
                "\n" +
                "    .es-header-body h3 a,\n" +
                "    .es-content-body h3 a,\n" +
                "    .es-footer-body h3 a {\n" +
                "        font-size: 20px !important;\n" +
                "    }\n" +
                "\n" +
                "    .es-menu td a {\n" +
                "        font-size: 14px !important;\n" +
                "    }\n" +
                "\n" +
                "    .es-header-body p,\n" +
                "    .es-header-body ul li,\n" +
                "    .es-header-body ol li,\n" +
                "    .es-header-body a {\n" +
                "        font-size: 10px !important;\n" +
                "    }\n" +
                "\n" +
                "    .es-content-body p,\n" +
                "    .es-content-body ul li,\n" +
                "    .es-content-body ol li,\n" +
                "    .es-content-body a {\n" +
                "        font-size: 16px !important;\n" +
                "    }\n" +
                "\n" +
                "    .es-footer-body p,\n" +
                "    .es-footer-body ul li,\n" +
                "    .es-footer-body ol li,\n" +
                "    .es-footer-body a {\n" +
                "        font-size: 12px !important;\n" +
                "    }\n" +
                "\n" +
                "    .es-infoblock p,\n" +
                "    .es-infoblock ul li,\n" +
                "    .es-infoblock ol li,\n" +
                "    .es-infoblock a {\n" +
                "        font-size: 12px !important;\n" +
                "    }\n" +
                "\n" +
                "    *[class=\"gmail-fix\"] {\n" +
                "        display: none !important;\n" +
                "    }\n" +
                "\n" +
                "    .es-m-txt-c,\n" +
                "    .es-m-txt-c h1,\n" +
                "    .es-m-txt-c h2,\n" +
                "    .es-m-txt-c h3 {\n" +
                "        text-align: center !important;\n" +
                "    }\n" +
                "\n" +
                "    .es-m-txt-r,\n" +
                "    .es-m-txt-r h1,\n" +
                "    .es-m-txt-r h2,\n" +
                "    .es-m-txt-r h3 {\n" +
                "        text-align: right !important;\n" +
                "    }\n" +
                "\n" +
                "    .es-m-txt-l,\n" +
                "    .es-m-txt-l h1,\n" +
                "    .es-m-txt-l h2,\n" +
                "    .es-m-txt-l h3 {\n" +
                "        text-align: left !important;\n" +
                "    }\n" +
                "\n" +
                "    .es-m-txt-r img,\n" +
                "    .es-m-txt-c img,\n" +
                "    .es-m-txt-l img {\n" +
                "        display: inline !important;\n" +
                "    }\n" +
                "\n" +
                "    .es-button-border {\n" +
                "        display: block !important;\n" +
                "    }\n" +
                "\n" +
                "    a.es-button,\n" +
                "    button.es-button {\n" +
                "        font-size: 14px !important;\n" +
                "        display: block !important;\n" +
                "        border-left-width: 0px !important;\n" +
                "        border-right-width: 0px !important;\n" +
                "    }\n" +
                "\n" +
                "    .es-btn-fw {\n" +
                "        border-width: 10px 0px !important;\n" +
                "        text-align: center !important;\n" +
                "    }\n" +
                "\n" +
                "    .es-adaptive table,\n" +
                "    .es-btn-fw,\n" +
                "    .es-btn-fw-brdr,\n" +
                "    .es-left,\n" +
                "    .es-right {\n" +
                "        width: 100% !important;\n" +
                "    }\n" +
                "\n" +
                "    .es-content table,\n" +
                "    .es-header table,\n" +
                "    .es-footer table,\n" +
                "    .es-content,\n" +
                "    .es-footer,\n" +
                "    .es-header {\n" +
                "        width: 100% !important;\n" +
                "        max-width: 600px !important;\n" +
                "    }\n" +
                "\n" +
                "    .es-adapt-td {\n" +
                "        display: block !important;\n" +
                "        width: 100% !important;\n" +
                "    }\n" +
                "\n" +
                "    .adapt-img {\n" +
                "        width: 100% !important;\n" +
                "        height: auto !important;\n" +
                "    }\n" +
                "\n" +
                "    .es-m-p0 {\n" +
                "        padding: 0px !important;\n" +
                "    }\n" +
                "\n" +
                "    .es-m-p0r {\n" +
                "        padding-right: 0px !important;\n" +
                "    }\n" +
                "\n" +
                "    .es-m-p0l {\n" +
                "        padding-left: 0px !important;\n" +
                "    }\n" +
                "\n" +
                "    .es-m-p0t {\n" +
                "        padding-top: 0px !important;\n" +
                "    }\n" +
                "\n" +
                "    .es-m-p0b {\n" +
                "        padding-bottom: 0 !important;\n" +
                "    }\n" +
                "\n" +
                "    .es-m-p20b {\n" +
                "        padding-bottom: 20px !important;\n" +
                "    }\n" +
                "\n" +
                "    .es-mobile-hidden,\n" +
                "    .es-hidden {\n" +
                "        display: none !important;\n" +
                "    }\n" +
                "\n" +
                "    tr.es-desk-hidden,\n" +
                "    td.es-desk-hidden,\n" +
                "    table.es-desk-hidden {\n" +
                "        width: auto !important;\n" +
                "        overflow: visible !important;\n" +
                "        float: none !important;\n" +
                "        max-height: inherit !important;\n" +
                "        line-height: inherit !important;\n" +
                "    }\n" +
                "\n" +
                "    tr.es-desk-hidden {\n" +
                "        display: table-row !important;\n" +
                "    }\n" +
                "\n" +
                "    table.es-desk-hidden {\n" +
                "        display: table !important;\n" +
                "    }\n" +
                "\n" +
                "    td.es-desk-menu-hidden {\n" +
                "        display: table-cell !important;\n" +
                "    }\n" +
                "\n" +
                "    .es-menu td {\n" +
                "        width: 1% !important;\n" +
                "    }\n" +
                "\n" +
                "    table.es-table-not-adapt,\n" +
                "    .esd-block-html table {\n" +
                "        width: auto !important;\n" +
                "    }\n" +
                "\n" +
                "    table.es-social {\n" +
                "        display: inline-block !important;\n" +
                "    }\n" +
                "\n" +
                "    table.es-social td {\n" +
                "        display: inline-block !important;\n" +
                "    }\n" +
                "}" +
                "</style>"
                ;


        return email;
    }



}
